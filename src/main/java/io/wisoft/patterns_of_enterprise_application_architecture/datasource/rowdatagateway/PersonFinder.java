package io.wisoft.patterns_of_enterprise_application_architecture.datasource.rowdatagateway;

import io.wisoft.patterns_of_enterprise_application_architecture.base.registry.sigleton.Registry;
import io.wisoft.patterns_of_enterprise_application_architecture.db.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonFinder {
  private final static String findStatementString = "SELECT id, lastname, firstname, number_of_dependents " +
      "FROM people WHERE id = ?";

  public PersonGateway find(Long id) {
    PersonGateway result = (PersonGateway) Registry.getPerson(id);
    if(result != null) {
      return result;
    }

    PreparedStatement findStatement = null;
    ResultSet rs = null;

    try {
      findStatement = DB.connect(findStatementString);
      findStatement.setLong(1, id.longValue());
      rs = findStatement.executeQuery();
      rs.next();
      result = PersonGateway.load(rs);
      return result;
    } catch (SQLException e) {
      throw new ApplicationException(e);
    } finally { DB.cleanUp(findStatement, rs);}
  }

  public PersonGateway find(long id) {
    return find(new Long(id));
  }

  private static final String findResponsibleStatement = "SELECT id, lastname, firstname, number_of_dependents " +
      "FROM people WHERE number_of_dependents > 0";

  public List findResponsibles() {
    List result = new ArrayList();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DB.prepare(findResponsibleStatement);
      rs = stmt.executeQuery();
      while(rs.next()) {
        result.add(PersonGateway.load(rs));
      }
      return result;
    } catch (SQLException e) {
      throw new ApplicationException(e);
    } finally { DB.cleanUp(stmt, rs); }
  }
}
