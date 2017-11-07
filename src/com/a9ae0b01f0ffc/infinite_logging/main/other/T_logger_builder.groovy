package other

import base.T_logging_base_6_util
import groovy.transform.ToString
import groovy.util.slurpersupport.GPathResult
import other.destinations.T_destination
import other.destinations.T_destination_file
import other.destinations.T_destination_shell
import other.formatters.T_event_formatter
import other.formatters.T_event_formatter_csv
import other.formatters.T_event_formatter_xml_hierarchical

@ToString(includeNames = true, includeFields = true)
class T_logger_builder extends T_logging_base_6_util {

    GPathResult p_conf = GC_NULL_OBJ_REF as GPathResult

    T_logger create_logger(GPathResult i_log_conf, String i_commons_conf_file_name, Boolean is_no_async = GC_FALSE, Closure i_dynamic_name_closure = GC_NULL_OBJ_REF as Closure) {
        p_conf = i_log_conf
        T_logger l_logger = new T_logger()
        for (l_destination_xml in p_conf.children()) {
            l_logger.add_destination(init_destination(l_destination_xml as GPathResult, i_commons_conf_file_name, is_no_async, i_dynamic_name_closure))
        }
        return l_logger
    }

    T_logger create_logger(String i_conf_file_name, String i_commons_conf_file_name, Boolean is_no_async = GC_FALSE, Closure i_dynamic_name_closure = GC_NULL_OBJ_REF as Closure) {
        T_logger l_logger = create_logger((GPathResult) new XmlSlurper().parse(i_conf_file_name), i_commons_conf_file_name, is_no_async, i_dynamic_name_closure)
        l_logger.set_commons_conf_file_name(i_commons_conf_file_name)
        return l_logger
    }

    static T_destination init_destination(GPathResult T_destination_xml, String i_commons_conf_file_name, Boolean is_no_async = GC_FALSE, Closure i_dynamic_name_closure = GC_NULL_OBJ_REF as Closure) {
        T_destination l_destination
        if (T_destination_xml.name() == GC_DESTINATION_FILE) {
            l_destination = new T_destination_file()
        } else if (T_destination_xml.name() == GC_DESTINATION_SHELL) {
            l_destination = new T_destination_shell()
        } else {
            l_destination = Class.forName(T_destination_xml.name()).newInstance() as T_destination
        }
        if (!T_destination_xml.@formatter.isEmpty()) {
            T_event_formatter l_event_formatter
            if (T_destination_xml.@formatter.text() == GC_EVENT_FORMATTER_CSV) {
                l_event_formatter = new T_event_formatter_csv()
            } else if (T_destination_xml.@formatter.text() == GC_DEVENT_FORMATTER_XML_HIERARCHICAL) {
                l_event_formatter = new T_event_formatter_xml_hierarchical()
            } else {
                l_event_formatter = Class.forName(T_destination_xml.@formatter.text()).newInstance() as T_event_formatter
            }
            l_event_formatter.set_parent_destination(l_destination)
            l_destination.set_formatter(l_event_formatter)
        } else {
            throw new E_application_exception(s.FORMATTER_IS_MANDATORY_FOR_DESTINATIONS, T_destination_xml.name())
        }
        if (!T_destination_xml.@location.isEmpty()) {
            l_destination.set_location(T_destination_xml.@location.text())

        }
        if (!T_destination_xml.@auto_zip.isEmpty()) {
            if (T_destination_xml.@auto_zip.text() == GC_TRUE_STRING) {
                l_destination.set_auto_zip(GC_TRUE)
            }
        }
        for (l_event_xml in T_destination_xml.children()) {
            l_destination.add_configuration_event(init_event((GPathResult) l_event_xml))
        }
        /*\/\/\/ This should always come last, as we clone the created destination*/
        if (is_not_null(i_dynamic_name_closure)) {
            l_destination.set_dynamic_location_closure(i_dynamic_name_closure)
        }
        if (!T_destination_xml.@async.isEmpty()) {
            if (T_destination_xml.@async.text() == GC_TRUE_STRING && not(is_no_async)) {
                l_destination.set_async_storage(new T_async_storage(l_destination.clone_with_no_async(), i_commons_conf_file_name, Thread.currentThread()))
                l_destination.p_async_storage.setDaemon(GC_FALSE)
                l_destination.p_async_storage.setName(Thread.currentThread().getName() + GC_LOGGER_THREAD_NAME_SUFFIX)
                if (T_destination_xml.@async_mode.text() == GC_ASYNC_MODE_REALTIME) {
                    l_destination.p_async_storage.set_mode(GC_ASYNC_MODE_REALTIME)
                    l_destination.p_async_storage.start()
                } else if (T_destination_xml.@async_mode.text() == GC_ASYNC_MODE_FLUSH) {
                    l_destination.p_async_storage.set_mode(GC_ASYNC_MODE_FLUSH)
                } else {
                    throw new E_application_exception(s.Unsupported_async_mode_Z1, T_destination_xml.@async_mode.text())
                }
            }
        }
        return l_destination
    }

    static T_event init_event(GPathResult i_event_xml) {
        T_event l_event = new T_event()
        l_event.set_event_type(i_event_xml.name())
        return l_event
    }


}
