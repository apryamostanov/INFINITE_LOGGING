package other

import other.destinations.T_destination

import java.util.concurrent.LinkedBlockingQueue

import static base.T_logging_base_5_context.*

class T_async_storage extends Thread {

    T_destination p_synchronous_destination = GC_NULL_OBJ_REF as T_destination
    String p_conf_name = GC_EMPTY_STRING
    Thread p_parent_thread = GC_NULL_OBJ_REF as Thread
    LinkedBlockingQueue<T_event> p_event_queue = new LinkedBlockingQueue<T_event>()
    String p_mode = GC_EMPTY_STRING

    T_async_storage(T_destination i_synchronous_destination, String i_conf_file_name, Thread i_parent_thread) {
        p_conf_name = i_conf_file_name
        p_synchronous_destination = i_synchronous_destination
        p_parent_thread = i_parent_thread
    }

    T_async_storage clone() {
        T_async_storage l_async_storage = new T_async_storage(p_synchronous_destination, p_conf_name, p_parent_thread)
        l_async_storage.set_mode(p_mode)
        l_async_storage.setName(getName())
        return l_async_storage
    }

    void set_mode(String i_mode) {
        p_mode = i_mode
    }

    @Override
    void run() {
        try {
            Boolean LC_IS_NO_ASYNC_TRUE = GC_TRUE
            if (is_not_null(p_conf_name)) {
                init_custom(p_conf_name, LC_IS_NO_ASYNC_TRUE)
            } else {
                init_default()
            }
            if (p_mode == GC_ASYNC_MODE_REALTIME) {
                while (GC_TRUE) {
                    while (not(p_event_queue.isEmpty())) {
                        p_synchronous_destination.store(p_event_queue.poll())
                    }
                    synchronized (this) {
                        try {
                            if (isInterrupted()) {
                                return
                            }
                            wait()
                        } catch (InterruptedException e_interrupted) {
                            return
                        }
                    }
                }
            } else {
                while (not(p_event_queue.isEmpty())) {
                    p_synchronous_destination.store(p_event_queue.poll())
                }
            }
        } finally {
            deinit()
        }
    }

}