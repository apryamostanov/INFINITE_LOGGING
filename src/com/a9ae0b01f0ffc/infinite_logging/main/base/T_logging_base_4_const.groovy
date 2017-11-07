package base

class T_logging_base_4_const extends T_common_base_3_utils {

    static final String GC_LOGGER_BUILDER_INTERFACE = "T_logger_builder"
    static final String GC_TRACE_SOURCE_RUNTIME = "runtime"
    static final String GC_TRACE_SOURCE_CONTEXT = "context"
    static final String GC_TRACE_SOURCE_EXCEPTION_TRACES = "exception_traces"
    static final String GC_TRACE_MASK_ALL = "all"
    static final String GC_TRACE_MASK_SENSITIVE = "sensitive"
    static final String GC_TRACE_MASK_ALL_EXCEPT_NON_SENSITIVE = "except_non_sensitive"
    static final String GC_TRACE_MASK_NONE = "none"
    static final String GC_STATEMENT_NAME_METHOD = GC_EMPTY_STRING
    static final String GC_STATEMENT_NAME_CLOSURE = "closure"
    static final String GC_BLACK_BOX_TYPE_ERROR = "error"
    static final String GC_BLACK_BOX_TYPE_PROFILE = "profile"
    static final String GC_BLACK_BOX_TYPE_FULL = "full"
    static final String GC_EVENT_TYPE_ALL = "all"
    static final String GC_EVENT_TYPE_METHOD_ENTER = "method_enter"
    static final String GC_EVENT_TYPE_METHOD_EXIT = "method_exit"
    static final String GC_EVENT_TYPE_RESULT = "result"
    static final String GC_EVENT_TYPE_STATEMENT_ENTER = "statement_enter"
    static final String GC_EVENT_TYPE_EXPRESSION_ENTER = "expression_enter"
    static final String GC_EVENT_TYPE_STATEMENT_EXIT = "statement_exit"
    static final String GC_EVENT_TYPE_EXPRESSION_EXIT = "expression_exit"
    static final String GC_EVENT_TYPE_INFO = "info"
    static final String GC_EVENT_TYPE_WARNING = "warning"
    static final String GC_EVENT_TYPE_DEBUG = "debug"
    static final String GC_EVENT_TYPE_TRACES = "traces"
    static final String GC_EVENT_TYPE_METHOD_ERROR = "method_error"
    static final String GC_EVENT_TYPE_STATEMENT_ERROR = "statement_error"
    static final String GC_EVENT_TYPE_EXPRESSION_ERROR = "expression_error"
    static final String GC_EVENT_TYPE_RECEIVE_TCP = "receive_tcp"
    static final String GC_EVENT_TYPE_SEND_TCP = "send_tcp"
    static final String GC_EVENT_TYPE_RECEIVE_I2C = "receive_i2c"
    static final String GC_EVENT_TYPE_SEND_I2C = "send_i2c"
    static final String GC_EVENT_TYPE_RECEIVE_SERIAL = "receive_serial"
    static final String GC_EVENT_TYPE_SEND_SERIAL = "send_serial"
    static final String GC_EVENT_TYPE_RECEIVE_SPI = "receive_spi"
    static final String GC_EVENT_TYPE_SEND_SPI = "send_spi"
    static final String GC_EVENT_TYPE_RECEIVE_HTTP = "receive_http"
    static final String GC_EVENT_TYPE_SEND_HTTP = "send_http"
    static final String GC_EVENT_TYPE_RECEIVE_SQL = "receive_sql"
    static final String GC_EVENT_TYPE_SEND_SQL = "send_sql"
    static final String GC_EVENT_TYPE_RECEIVE_BLUETOOTH = "receive_bluetooth"
    static final String GC_EVENT_TYPE_SEND_BLUETOOTH = "send_bluetooth"
    static final String GC_EXCEPTION_VARIABLE_NAME = "e_others"
    static final Class GC_UTILITY_CLASS = T_logging_base_6_util
    static final String GC_HYPHEN = "-"
    static final String GC_BLACK_BOX_COMPILER_CONFIG_PATH = "C:/COMPILE/with_logging/commons.conf"
    static final String GC_EXECUTION_NODE_TYPE_METHOD = "method"
    static final String GC_EXECUTION_NODE_TYPE_STATEMENT = "statement"
    static final String GC_EXECUTION_NODE_TYPE_EXPRESSION = "expression"
    static final String GC_ASYNC_MODE_REALTIME = "realtime" //todo rename to continious
    static final String GC_ASYNC_MODE_FLUSH = "flush"
    static final String GC_ZIP_SUFFIX = ".zip"
    static final String GC_LOGGER_THREAD_NAME_SUFFIX = "L"
    static final String GC_CONTEXT_NAME_OPERATION_START_TIME = "operation_start_time"
    static final String GC_CONTEXT_NAME_METHOD_NAME = "method_name"
    static final String GC_DATETIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS"
    static final String GC_FILENAME_DATE_FORMAT = "yyyyMMdd"
    static final String GC_FILENAME_TIME_FORMAT = "HHmmssSSS"
    static final String GC_CLASS_LOADER_CONF_FILE_NAME = GC_EMPTY_STRING
    static final String GC_MESSAGE_FORMAT_TOKEN_SEPARATOR_BEFORE = "_"
    static final String GC_MESSAGE_FORMAT_TOKEN_SEPARATOR_AFTER = " "
    static final String GC_MESSAGE_FORMAT_TOKEN_TRACE = "Z"
    static final String GC_SUBST_USERNAME = "%USERNAME%"
    static final String GC_SUBST_DATE = "%DATE%"
    static final String GC_SUBST_TIME = "%TIME%"
    static final String GC_SUBST_THREADID = "%THREADID%"
    static final String GC_SUBST_PROCESSID = "%PROCESSID%"
    static final String GC_SUBST_THREADNAME = "%THREADNAME%"
    static final String GC_SUBST_DYNAMIC = "%DYNAMIC%"
    static final String GC_DESTINATION_FILE = "file"
    static final String GC_DESTINATION_SHELL = "shell"
    static final String GC_EVENT_FORMATTER_CSV = "csv"
    static final String GC_DEVENT_FORMATTER_XML_HIERARCHICAL = "xml_hierarchical"
    static final String GC_DEFAULT_LOGGER_CONFIG = """<?xml version="1.0" encoding="UTF-8"?>
<logger>
    <shell formatter="csv">
        <info/>
        <warning/>
        <method_error/>
    </shell>
</logger>
"""

}
