package io.wisoft.patterns_of_enterprise_application_architecture.datasource.rowdatagateway;

import io.wisoft.patterns_of_enterprise_application_architecture.db.DB;

import java.sql.*;


public class PersonGateway {

  private String lastName;
  private String firstName;
  private int numberOfDependents;

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public int getNumberOfDependents() {
    return numberOfDependents;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setNumberOfDependents(int numberOfDependents) {
    this.numberOfDependents = numberOfDependents;
  }

  private static final String updateStatementString =
      "UPDATE people" +
          "set lastname =?, firstname = ?, number_of_dependents = ?" +
          "where id = ?";

  public void update() {
    try (var conn = DB.connect();
         var updateStatement = conn.prepareStatement(updateStatementString)) {

      updateStatement.setString(1, lastName);
      updateStatement.setString(2, firstName);
      updateStatement.setInt(3, numberOfDependents);
//      updateStatement.setInt(4,getId.intValue());
      updateStatement.execute();

    } catch (SQLException e) {
      throw new RuntimeException("Failed to update player data", e);
    }
  }

  private static final String insertStatementString =  "INSERT INTO people VALUES(?,?,?,?)";

//  public Long insert() {
//    PreparedStatement insertStatement = null;
//    try (var conn = DB.connect();
//         var updateStatement = conn.prepareStatement(insertStatementString)) {
//      setID(findNextDatabaseId());
//      insertStatement.setInt(1, getID().intValue());
//      insertStatement.setString(2, lastName);
//      insertStatement.setString(3, firstName);
//      insertStatement.setInt(4, numberOfDependents);
//      insertStatement.execute();
//      Registry.addPerson(this);
//      return getID();
//    } catch (SQLException e) {
//      throw new ApplicationException(e);
//    } finally { DB.cleanUp(insertStatement);}
//  }
//
//  public static PersonGateway load(ResultSet rs) throws SQLException {
//    Long id = new Long(rs.getLong(1));
//    PersonGateway result = (PersonGateway) Registry.getPerson(id);
//    if(result != null) {
//      return result;
//    }
//
//    String lastNameArg = rs.getString(2);
//    String firstNameArg = rs.getString(3);
//    int numDependentArg = rs.getInt(4);
//
//    result = new PersonGateway(id, lastNameArg, firstNameArg, numDependentArg);
//    Registry.addPerson(result);
//
//    return result;
//  }
}
