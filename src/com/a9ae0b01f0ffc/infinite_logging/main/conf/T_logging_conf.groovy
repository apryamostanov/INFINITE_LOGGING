package conf

import other.T_common_conf
import static base.T_logging_base_4_const.*

class T_logging_conf extends T_common_conf {

    String GC_BLACK_BOX_ENABLED
    String GC_DEFAULT_LOGGER_CONF_FILE_NAME
    String GC_LOG_CSV_SEPARATOR
    String GC_ARGUMENT_POSITIONAL_NAME
    String GC_INITIAL_STARTING_EXECUTION_NODE_NAME
    String GC_DEFAULT_GUID
    String GC_DEFAULT_TRACE
    String GC_DEFAULT_TRACE_MASKED
    String GC_DEFAULT_TRACE_MUTED
    String GC_DEFAULT_TRACE_NAME
    String GC_XML_PAD_DEPTH
    String GC_JUL_CONF_FILE_NAME
    String GC_PROFILE_ALL
    String GC_MAX_TRACE_LENGTH
    String GC_MAX_CODE_LENGTH
    String GC_MAX_CODE_ELEMENT_LENGTH
    String GC_ASYNC_STORER_POLL_PERIOD_MILLIS
    String GC_ASYNC_STORER_STORE_PERIOD_MILLIS
    String GC_DYNAMIC_TOKEN_CODE

    T_logging_conf(String i_conf_file_name) {
        super(i_conf_file_name)
    }

    T_logging_conf() {
        System.out.println(s.Using_default_configuration)
    }

    @Override
    void refresh_config() {
        GC_BLACK_BOX_ENABLED = GC_TRUE_STRING
        GC_DEFAULT_LOGGER_CONF_FILE_NAME = GC_EMPTY_STRING
        GC_LOG_CSV_SEPARATOR = "|"
        GC_ARGUMENT_POSITIONAL_NAME = "Argument#"
        GC_INITIAL_STARTING_EXECUTION_NODE_NAME = "Unknown_class.unknown_method"
        GC_DEFAULT_GUID = "Unknown GUID"
        GC_DEFAULT_TRACE = "Trace missing"
        GC_DEFAULT_TRACE_MASKED = "Trace masked"
        GC_DEFAULT_TRACE_MUTED = "Trace muted"
        GC_DEFAULT_TRACE_NAME = "unnamed"
        GC_XML_PAD_DEPTH = "4"
        GC_JUL_CONF_FILE_NAME = "./conf/logging.properties"
        GC_PROFILE_ALL = "true"
        GC_MAX_TRACE_LENGTH = "512"
        GC_MAX_CODE_LENGTH = "128"
        GC_MAX_CODE_ELEMENT_LENGTH = "256"
        GC_ASYNC_STORER_POLL_PERIOD_MILLIS = "100"
        GC_ASYNC_STORER_STORE_PERIOD_MILLIS = "50"
        //
        GC_BLACK_BOX_ENABLED = nvl_empty_map(get_conf().black_box_enabled, GC_BLACK_BOX_ENABLED)
        GC_DEFAULT_LOGGER_CONF_FILE_NAME = nvl_empty_map(get_conf().default_logger_conf_file_name, GC_DEFAULT_LOGGER_CONF_FILE_NAME)
        GC_LOG_CSV_SEPARATOR = nvl_empty_map(get_conf().log_csv_separator, GC_LOG_CSV_SEPARATOR)
        GC_ARGUMENT_POSITIONAL_NAME = nvl_empty_map(get_conf().argument_positional_name, GC_ARGUMENT_POSITIONAL_NAME)
        GC_INITIAL_STARTING_EXECUTION_NODE_NAME = nvl_empty_map(get_conf().initial_starting_execution_node_name, GC_INITIAL_STARTING_EXECUTION_NODE_NAME)
        GC_DEFAULT_GUID = nvl_empty_map(get_conf().default_guid, GC_DEFAULT_GUID)
        GC_DEFAULT_TRACE = nvl_empty_map(get_conf().default_trace, GC_DEFAULT_TRACE)
        GC_DEFAULT_TRACE_MASKED = nvl_empty_map(get_conf().default_trace_masked, GC_DEFAULT_TRACE_MASKED)
        GC_DEFAULT_TRACE_MUTED = nvl_empty_map(get_conf().default_trace_muted, GC_DEFAULT_TRACE_MUTED)
        GC_XML_PAD_DEPTH = nvl_empty_map(get_conf().xml_pad_depth, GC_XML_PAD_DEPTH)
        GC_DEFAULT_TRACE_NAME = nvl_empty_map(get_conf().default_trace_name, GC_DEFAULT_TRACE_NAME)
        GC_JUL_CONF_FILE_NAME = nvl_empty_map(get_conf().jul_conf_file_name, GC_JUL_CONF_FILE_NAME)
        GC_PROFILE_ALL = nvl_empty_map(get_conf().profile_all, GC_PROFILE_ALL)
        GC_MAX_TRACE_LENGTH = nvl_empty_map(get_conf().max_trace_length, GC_MAX_TRACE_LENGTH)
        GC_MAX_CODE_LENGTH = nvl_empty_map(get_conf().max_code_length, GC_MAX_CODE_LENGTH)
        GC_ASYNC_STORER_POLL_PERIOD_MILLIS = nvl_empty_map(get_conf().async_storer_poll_period_millis, GC_ASYNC_STORER_POLL_PERIOD_MILLIS)
        GC_ASYNC_STORER_STORE_PERIOD_MILLIS = nvl_empty_map(get_conf().async_storer_store_period_millis, GC_ASYNC_STORER_STORE_PERIOD_MILLIS)
        GC_DYNAMIC_TOKEN_CODE = nvl_empty_map(get_conf().dynamic_token_code, GC_DYNAMIC_TOKEN_CODE)
    }
}
