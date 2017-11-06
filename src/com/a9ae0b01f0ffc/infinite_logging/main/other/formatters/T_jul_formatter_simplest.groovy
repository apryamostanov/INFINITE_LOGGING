package other.formatters

import java.util.logging.LogRecord

class T_jul_formatter_simplest extends java.util.logging.Formatter {
    @Override
    String format(LogRecord record) {
        return record.message
    }
}
