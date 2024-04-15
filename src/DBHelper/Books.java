package DBHelper;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Books extends DBHelper {
	private final String TABLE_NAME = "Books";
	public static final String bookID = "bookID";
	public static final String Title = "Title";
	public static final String Author = "Author";
	public static final String Genre = "Genre";
	public static final String Status = "Status";
	public static final String dueDate = "dueDate";

	/**
	 * Prepares the text of a SQL "select" command
	 * @param fields field that will be selected from the database
	 * @param whatField field that will be checked for searching
	 * @param whatValue condition to search by
	 * @param sortField field that will be checked for sorting
	 * @param sort condition to sort by
	 * @return
	 */
	private String prepareSQL(String fields, String whatField, String whatValue, String sortField, String sort) {
		String query = "SELECT ";
		query += fields == null ? " * FROM " + TABLE_NAME : fields + " FROM " + TABLE_NAME;
		query += whatField != null && whatValue != null ? " WHERE " + whatField + " = \"" + whatValue + "\"" : "";
		query += sort != null && sortField != null ? " order by " + sortField + " " + sort : "";
		return query;
	}

	/**
	 * insert a new record into the database, each param is a field in the database
	 * @param bookID bookID field
	 * @param Title Tittle field
	 * @param Author Author field
	 * @param Genre Genre field
	 * @param Status Status field
	 * @param dueDate dueDate field
	 */
	public void insert(Integer bookID, String Title, String Author, String Genre, String Status, String dueDate) {
		Title = Title != null ? "\"" + Title + "\"" : null;
		Author = Author != null ? "\"" + Author + "\"" : null;
		Genre = Genre != null ? "\"" + Genre + "\"" : null;
		Status = Status != null ? "\"" + Status + "\"" : null;
		dueDate = dueDate != null ? "\"" + dueDate + "\"" : null;
		
		Object[] values_ar = {bookID, Title, Author, Genre, Status, dueDate};
		String[] fields_ar = {Books.bookID, Books.Title, Books.Author, Books.Genre, Books.Status, Books.dueDate};
		String values = "", fields = "";
		for (int i = 0; i < values_ar.length; i++) {
			if (values_ar[i] != null) {
				values += values_ar[i] + ", ";
				fields += fields_ar[i] + ", ";
			}
		}
		if (!values.isEmpty()) {
			values = values.substring(0, values.length() - 2);
			fields = fields.substring(0, fields.length() - 2);
			super.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ");");
		}
	}

	/**
	 * delete a record from the database
	 * @param whatField field that will be checked for searching
	 * @param whatValue condition to search by
	 */
	public void delete(String whatField, String whatValue) {
		super.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue + ";");
	}

	/**
	 * @param whatField field that will be checked for searching
	 * @param whatValue condition to search by
	 * @param whereField will update field to this
	 * @param whereValue will update field value to this
	 */
	public void update(String whatField, String whatValue, String whereField, String whereValue) {
		super.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue + "\" where " + whereField + " = \"" + whereValue + "\";");
	}

	public ArrayList<ArrayList<Object>> select(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQuery(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return super.executeQuery(query);
	}

	public void execute(String query) {
		super.execute(query);
	}

	public DefaultTableModel selectToTable(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQueryToTable(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

}