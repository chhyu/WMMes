package com.weimi.wmmess.business.shimu.bean.step7;

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
 * DAO for table "ZHU_SU_RESBEAN".
*/
public class ZhuSuResbeanDao extends AbstractDao<ZhuSuResbean, Long> {

    public static final String TABLENAME = "ZHU_SU_RESBEAN";

    /**
     * Properties of entity ZhuSuResbean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ZhuSuResbeanId = new Property(1, Long.class, "zhuSuResbeanId", false, "ZHU_SU_RESBEAN_ID");
        public final static Property ZhuSuSuDu = new Property(2, String.class, "zhuSuSuDu", false, "ZHU_SU_SU_DU");
        public final static Property ZhuSuTime = new Property(3, String.class, "zhuSuTime", false, "ZHU_SU_TIME");
        public final static Property YaLiMax = new Property(4, String.class, "yaLiMax", false, "YA_LI_MAX");
        public final static Property YaliAndTime = new Property(5, String.class, "yaliAndTime", false, "YALI_AND_TIME");
    }

    private Query<ZhuSuResbean> step7Resbean_ZhuSuResbeanListQuery;

    public ZhuSuResbeanDao(DaoConfig config) {
        super(config);
    }
    
    public ZhuSuResbeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ZHU_SU_RESBEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ZHU_SU_RESBEAN_ID\" INTEGER NOT NULL ," + // 1: zhuSuResbeanId
                "\"ZHU_SU_SU_DU\" TEXT NOT NULL ," + // 2: zhuSuSuDu
                "\"ZHU_SU_TIME\" TEXT NOT NULL ," + // 3: zhuSuTime
                "\"YA_LI_MAX\" TEXT NOT NULL ," + // 4: yaLiMax
                "\"YALI_AND_TIME\" TEXT NOT NULL );"); // 5: yaliAndTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ZHU_SU_RESBEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ZhuSuResbean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getZhuSuResbeanId());
        stmt.bindString(3, entity.getZhuSuSuDu());
        stmt.bindString(4, entity.getZhuSuTime());
        stmt.bindString(5, entity.getYaLiMax());
        stmt.bindString(6, entity.getYaliAndTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ZhuSuResbean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getZhuSuResbeanId());
        stmt.bindString(3, entity.getZhuSuSuDu());
        stmt.bindString(4, entity.getZhuSuTime());
        stmt.bindString(5, entity.getYaLiMax());
        stmt.bindString(6, entity.getYaliAndTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ZhuSuResbean readEntity(Cursor cursor, int offset) {
        ZhuSuResbean entity = new ZhuSuResbean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // zhuSuResbeanId
            cursor.getString(offset + 2), // zhuSuSuDu
            cursor.getString(offset + 3), // zhuSuTime
            cursor.getString(offset + 4), // yaLiMax
            cursor.getString(offset + 5) // yaliAndTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ZhuSuResbean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setZhuSuResbeanId(cursor.getLong(offset + 1));
        entity.setZhuSuSuDu(cursor.getString(offset + 2));
        entity.setZhuSuTime(cursor.getString(offset + 3));
        entity.setYaLiMax(cursor.getString(offset + 4));
        entity.setYaliAndTime(cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ZhuSuResbean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ZhuSuResbean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ZhuSuResbean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "zhuSuResbeanList" to-many relationship of Step7Resbean. */
    public List<ZhuSuResbean> _queryStep7Resbean_ZhuSuResbeanList(Long zhuSuResbeanId) {
        synchronized (this) {
            if (step7Resbean_ZhuSuResbeanListQuery == null) {
                QueryBuilder<ZhuSuResbean> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ZhuSuResbeanId.eq(null));
                step7Resbean_ZhuSuResbeanListQuery = queryBuilder.build();
            }
        }
        Query<ZhuSuResbean> query = step7Resbean_ZhuSuResbeanListQuery.forCurrentThread();
        query.setParameter(0, zhuSuResbeanId);
        return query.list();
    }

}
