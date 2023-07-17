package notebook.model;

import java.util.List;

public interface Operation<T> {
    List<T> readAll();
    void saveAll(List<T> data);
}
