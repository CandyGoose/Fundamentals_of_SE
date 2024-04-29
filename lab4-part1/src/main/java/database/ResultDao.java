package database;

import entity.Result;

import java.util.List;

public interface ResultDao {
    void save(Result result);

    void clear();

    List<Result> getAll();
}
