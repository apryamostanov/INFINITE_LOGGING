package base

import com.sun.org.apache.xerces.internal.util.XMLChar
import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.ASTNode
import other.T_event
import other.T_static_string
import other.T_trace
import other.destinations.T_destination

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class T_logging_base_6_util extends T_logging_base_5_context {

    static String ast2text(ASTNode i_ast_node) {
        StringWriter l_string_writer = new StringWriter()
        i_ast_node.visit(new AstNodeToScriptVisitor(l_string_writer))
        return l_string_writer.getBuffer().toString().replace("\$", GC_EMPTY_STRING)
    }

    static String code2element(String i_code_text) {
        String l_element_name = i_code_text.replace(GC_XML_GREATER, GC_HYPHEN).replace(GC_XML_LESS, GC_HYPHEN)
        String l_result_name = GC_EMPTY_STRING
        for (Integer l_char_index = GC_FIRST_INDEX; l_char_index < l_element_name.length(); ++l_char_index) {
            char l_char = l_element_name.charAt(l_char_index)
            if (XMLChar.isName(l_char as int)) {
                l_result_name += l_element_name.charAt(l_char_index)
            } else {
                l_result_name += GC_UNDERSCORE
            }
        }
        return l_result_name
    }

    static
    final String process_location(String i_location, T_event i_source_event, T_destination i_destination) {
        Date l_current_date = new Date()
        String l_location = i_location
        HashMap l_binding_map = new HashMap()
        l_binding_map.put("location", i_location)
        l_binding_map.put("source_event", i_source_event)
        l_binding_map.put("destination", i_destination)
        i_location = get_template_manager().get_template(i_location).make(l_binding_map).toString()
        l_location = l_location.replace(GC_SUBST_USERNAME, GC_USERNAME)
        l_location = l_location.replace(GC_SUBST_DATE, l_current_date.format(GC_FILENAME_DATE_FORMAT))
        l_location = l_location.replace(GC_SUBST_TIME, i_destination.get_init_date().format(GC_FILENAME_TIME_FORMAT))
        l_location = l_location.replace(GC_SUBST_THREADID, Long.toString(Thread.currentThread().getId()))
        l_location = l_location.replace(GC_SUBST_THREADNAME, Thread.currentThread().getName())
        l_location = l_location.replace(GC_SUBST_PROCESSID, GC_PROCESSID)
        if (is_not_null(i_destination.get_dynamic_location_closure())) {
            l_location = l_location.replace(GC_SUBST_DYNAMIC, i_destination.get_dynamic_location_closure().call(i_source_event) as String)
        }
        return l_location
    }

    static String code2attribute(String i_code_text) {
        String l_result = i_code_text.replace(GC_XML_GREATER, GC_HYPHEN).replace(GC_XML_LESS, GC_HYPHEN).replace(GC_XML_DOUBLE_QUOTE, GC_EMPTY_STRING).replace(GC_XML_QUOTE, GC_EMPTY_STRING).replace("{", GC_EMPTY_STRING).replace("}", GC_EMPTY_STRING).replace("\n", GC_EMPTY_STRING)
        l_result = l_result.substring(GC_FIRST_CHAR, Math.min(l_result.length(), new Integer(c().GC_MAX_CODE_ELEMENT_LENGTH)))
        return l_result
    }

    static Boolean is_primitive(String i_class_name_short) {
        if (["Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "Char", "String", "GString"].contains(i_class_name_short)) {
            return GC_TRUE
        } else {
            return GC_FALSE
        }
    }

    static String escape_xml(String i_unescaped_xml_string) {
        if (i_unescaped_xml_string.contains(GC_XML_DOUBLE_QUOTE)
                || i_unescaped_xml_string.contains(GC_XML_DOUBLE_QUOTE)
                || i_unescaped_xml_string.contains(GC_XML_LESS)
                || i_unescaped_xml_string.contains(GC_XML_GREATER)
                || i_unescaped_xml_string.contains(GC_XML_AMP)) {
            return GC_XML_CDATA_OPEN + i_unescaped_xml_string + GC_XML_CDATA_CLOSE
        } else {
            return i_unescaped_xml_string
        }
    }

    static T_trace r(Object i_object, String T_trace_name) {
        T_trace l_trace = object2trace(i_object)
        l_trace.set_name(T_trace_name)
        return l_trace
    }

    static T_trace t(Object i_object, T_static_string T_trace_name) {
        return r(i_object, T_trace_name.toString())
    }


    static T_trace object2trace(Object i_object) {
        T_trace l_trace = new T_trace()
        l_trace.set_ref(i_object)
        l_trace.set_val(i_object.toString())
        l_trace.set_name(c().GC_DEFAULT_TRACE_NAME)
        return l_trace
    }

    static String make_method_execution_node_name(String i_class_name, String i_method_name, Integer i_line_number) {
        return i_class_name + GC_POINT + i_method_name + GC_COLON + i_line_number.toString()
    }

    static Object run_closure(Closure i_closure) {
        System.out.println(i_closure.getClass().getName())
        for (l_method in i_closure.getClass().getDeclaredMethods()) {
            System.out.println(l_method.getName())
        }
        System.out.println(i_closure instanceof Class)
        return i_closure.call()
    }

    static ArrayList<T_trace> objects2traces(Collection<Object> i_objects) {
        ArrayList<T_trace> l_method_args = new ArrayList<T_trace>()
        for (Object l_object : i_objects) {
            if (l_object instanceof T_trace) {
                l_method_args.add((T_trace) l_object)
            } else {
                T_trace l_trace = object2trace(l_object)
                l_method_args.add(l_trace)
            }
        }
        return l_method_args
    }


    static ArrayList<T_trace> objects2traces_array(Object[] i_objects) {
        return objects2traces(Arrays.asList(i_objects))
    }

    static void zip_file(File i_file) {
        final Integer LC_BUFFER_LENGTH = 2048
        if (i_file.isFile()) {
            FileOutputStream l_file_output_stream = new FileOutputStream(i_file.getAbsolutePath() + GC_ZIP_SUFFIX)
            ZipOutputStream l_zip_output_stream = new ZipOutputStream(new BufferedOutputStream(l_file_output_stream))
            byte[] l_byte_array = new byte[LC_BUFFER_LENGTH]
            FileInputStream l_file_input_stream = new FileInputStream(i_file)
            BufferedInputStream l_buffered_input_stream = new BufferedInputStream(l_file_input_stream, LC_BUFFER_LENGTH)
            ZipEntry entry = new ZipEntry(i_file.getName())
            l_zip_output_stream.putNextEntry(entry)
            Integer l_count_bytes
            while ((l_count_bytes = l_buffered_input_stream.read(l_byte_array, GC_ZERO, LC_BUFFER_LENGTH)) != GC_CHAR_INDEX_NOT_EXISTING) {
                l_zip_output_stream.write(l_byte_array, GC_ZERO, l_count_bytes)
            }
            l_buffered_input_stream.close()
            l_zip_output_stream.close()
        }
    }

}
