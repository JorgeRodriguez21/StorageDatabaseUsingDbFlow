package uio.androidbootcamp.storagedatabaseusingdbflow.repositories;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by jrodri on 7/7/17.
 */

public class DataBaseRepository<T extends BaseModel> {

    final Class<T> typeParameterClass;

    public DataBaseRepository(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public boolean entityExists(T entity) {
        return entity.exists();
    }

    public boolean saveEntity(T entity) {
        return !entityExists(entity) && entity.save();
    }

    public boolean deleteEntity(T entity) {
        return entity.delete();
    }

    public boolean updateEntity(T entity) {
        return entity.update();
    }

    public List<T> getAllRecords(){return SQLite.select().from(typeParameterClass).queryList();}

}