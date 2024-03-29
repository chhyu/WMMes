package com.weimi.wmmess.business.shimu.bean.step3;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.weimi.wmmess.business.shimu.bean.DaoSession;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FIX_SIDE_RESBEAN".
*/
public class FixSideResbeanDao extends AbstractDao<FixSideResbean, Long> {

    public static final String TABLENAME = "FIX_SIDE_RESBEAN";

    /**
     * Properties of entity FixSideResbean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FixSideResbeanId = new Property(1, Long.class, "fixSideResbeanId", false, "fixSideResbeanId");
        public final static Property Index = new Property(2, int.class, "index", false, "INDEX");
        public final static Property Record = new Property(3, String.class, "record", false, "RECORD");
    }

    private Query<FixSideResbean> step3MuResBean_FixSideResbeanListQuery;

    public FixSideResbeanDao(DaoConfig config) {
        super(config);
    }
    
    public FixSideResbeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FIX_SIDE_RESBEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"fixSideResbeanId\" INTEGER," + // 1: fixSideResbeanId
                "\"INDEX\" INTEGER NOT NULL ," + // 2: index
                "\"RECORD\" TEXT);"); // 3: record
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FIX_SIDE_RESBEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FixSideResbean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long fixSideResbeanId = entity.getFixSideResbeanId();
        if (fixSideResbeanId != null) {
            stmt.bindLong(2, fixSideResbeanId);
        }
        stmt.bindLong(3, entity.getIndex());
 
        String record = entity.getRecord();
        if (record != null) {
            stmt.bindString(4, record);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FixSideResbean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long fixSideResbeanId = entity.getFixSideResbeanId();
        if (fixSideResbeanId != null) {
            stmt.bindLong(2, fixSideResbeanId);
        }
        stmt.bindLong(3, entity.getIndex());
 
        String record = entity.getRecord();
        if (record != null) {
            stmt.bindString(4, record);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FixSideResbean readEntity(Cursor cursor, int offset) {
        FixSideResbean entity = new FixSideResbean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // fixSideResbeanId
            cursor.getInt(offset + 2), // index
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // record
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FixSideResbean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFixSideResbeanId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setIndex(cursor.getInt(offset + 2));
        entity.setRecord(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FixSideResbean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FixSideResbean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FixSideResbean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "fixSideResbeanList" to-many relationship of Step3MuResBean. */
    public List<FixSideResbean> _queryStep3MuResBean_FixSideResbeanList(Long fixSideResbeanId) {
        synchronized (this) {
            if (step3MuResBean_FixSideResbeanListQuery == null) {
                QueryBuilder<FixSideResbean> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FixSideResbeanId.eq(null));
                step3MuResBean_FixSideResbeanListQuery = queryBuilder.build();
            }
        }
        Query<FixSideResbean> query = step3MuResBean_FixSideResbeanListQuery.forCurrentThread();
        query.setParameter(0, fixSideResbeanId);
        return query.list();
    }

}
