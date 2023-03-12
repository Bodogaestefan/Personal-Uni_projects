package DataAccess;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This class is used for creating and executing queries and also creating objects through reflction
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * Creating a select query
     *
     *
     *
     */

    private Object getId(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", type);
        Method method = propertyDescriptor.getReadMethod();
        return method.invoke(t);
    }
    /**
     * Creating a SELECT query with a WHERE clause
     *
     *
     *
     */
    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM pt_3.\"");
        sb.append(type.getSimpleName());
        sb.append("\" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Creating a SELECT query for all the rows in the table
     *
     *
     *
     */

    private String createSelectAllQuery() {
        return "SELECT * FROM pt_3.\"" + type.getSimpleName()+"\"";
    }

    private String createSelectAllQueryName() {
        return "SELECT name FROM pt_3.\"" + type.getSimpleName()+"\"";
    }

    /**
     * Creating an insert query
     *
     *
     *
     */

    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder("INSERT INTO pt_3.\"" + type.getSimpleName() + "\"");
        sb.append(" VALUES (");
        int columns = 0;
        for(Field field:type.getDeclaredFields()){
            columns++;
        }
        int i=0;
        for(Field field:type.getDeclaredFields()){
            field.setAccessible(true);
            sb.append("?");
            if(columns-1!= i)
                sb.append(",");
            i++;
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Creating a delete query
     *
     *
     *
     */
    private String createDeleteQuery(T toDelete) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        return "DELETE FROM pt_3.\"" + type.getSimpleName() + "\" WHERE id=" + getId(toDelete);
    }

    /**
     * Creating a JTable through reflection by getting the column names from the object types.
     *<p>
     *     The method receives a List of objects through a parameter and fills the table with the correponding values from the objects
     *</p>
     *
     *
     */

    public static JTable createTable(List<?> objects) {
        DefaultTableModel model = new DefaultTableModel();

        Class crtClass = objects.get(0).getClass();
        Field[] fields = crtClass.getDeclaredFields();

        Object[][] data = new Object[objects.size()][fields.length];
        Object[] columnNames = new String[fields.length];

        int index = 0, i = 0, j = 0;

        for (Field field : fields) {
            columnNames[index++] = field.getName();
        }
        for (Object obj : objects) {
            j = 0;
            for (Field field : fields) {
                PropertyDescriptor propertyDescriptor = null;
                try {

                    propertyDescriptor = new PropertyDescriptor(field.getName(), crtClass);
                    Method method = propertyDescriptor.getReadMethod(); //getter
                    data[i][j++] = method.invoke(obj);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
        JTable jTable = new JTable(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.getTableHeader().setReorderingAllowed(false);
        return jTable;
    }
    /**
     * Creating an update query
     *
     *
     *
     */
    private String createUpdateQuery(T toUpdate) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        StringBuilder sb = new StringBuilder("UPDATE pt_3.\"" + type.getSimpleName() + "\" SET ");
        for (Field field : type.getDeclaredFields()) {
                sb.append(field.getName() + "=?,");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(" WHERE id=" + getId(toUpdate));
        return sb.toString();
    }

    /**
     * Executing a findAll query which returns a list of objects
     *
     *
     *
     */

    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.try_connect();
            statement = connection.prepareStatement(createSelectAllQuery());
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
        return null;
    }

    /**
     * Executes an find by id query
     *
     *
     *
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.try_connect();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
        return null;
    }

    /**
     * Receives a ResultSet and returns a List of objects by using reflection to get the fields 
     *
     *
     *
     */

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Fills the ? in the statement
     * Used only for update and insert queries as the fields are not specified in the String query that was created
     */
    private PreparedStatement createStatement(PreparedStatement st, T t) throws InvocationTargetException, IllegalAccessException, IntrospectionException, SQLException {
        int index = 1;
        for (Field field : type.getDeclaredFields()) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            st.setObject(index, method.invoke(t));
            index++;
        }
        return st;
    }

    /**
     * Executes an insert query of an object
     */
    public T insert(T t) {
        Connection dbConnection = ConnectionFactory.try_connect();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            insertStatement = createStatement(insertStatement, t);
            insertStatement.executeUpdate();
            rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", type);
            Method method = propertyDescriptor.getWriteMethod();
            method.invoke(t, insertedId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Invalid Data");
        } finally {
            ConnectionFactory.closeConnection(dbConnection);
        }
        return t;
    }
    /**
     * Executes an update query of an object
     */
    public T update(T t) {
        Connection dbConnection = ConnectionFactory.try_connect();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(createUpdateQuery(t));
            updateStatement = createStatement(updateStatement, t);
            updateStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(dbConnection);
        }
        return t;
    }

    /**
     * Used to execute a delete query on the DB of an object passed as parameter
     * @param t
     */
    public void delete(T t) {
        Connection dbConnection = ConnectionFactory.try_connect();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(createDeleteQuery(t));
            deleteStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(dbConnection);
        }
    }
}
