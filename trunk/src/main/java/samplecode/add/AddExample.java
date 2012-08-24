package samplecode.add;


import com.unboundid.ldap.sdk.AddRequest;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPConnectionOptions;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldif.LDIFException;


/**
 * demonstrate the {@link AddRequest}
 */
public final class AddExample
{

  /**
   * demonstrate the {@link AddRequest}
   */
  public static void main(final String... args)
  {
    /*
    * Use connection options to specify that the connection attempt
    * should be 1 second and if the ADD request times out, the request
    * should be abandoned
    */
    final LDAPConnectionOptions connectionOptions = new LDAPConnectionOptions();
    connectionOptions.setAbandonOnTimeout(true);
    connectionOptions.setConnectTimeoutMillis(1000);

    /*
    * LDIF lines for the add request
    */
    final String[] ldifLines =
            {"dn: uid=user,dc=example,dc=com", "changetype: add", "cn: Joe User", "sn: User",
                    "uid: user", "userPassword: password"};

    final String host = "ldap.example.com";
    final int port = 389;
    try
    {
      final LDAPConnection ldapConnection = new LDAPConnection(connectionOptions, host, port);
      try
      {
        final LDAPResult ldapResult = ldapConnection.add(new AddRequest(ldifLines));
        System.out.println(ldapResult);
      }
      catch(LDIFException e)
      {
        System.err.println(e);
      }
      finally
      {
        ldapConnection.close();
      }
    }
    catch(LDAPException e)
    {
      System.err.println(e);
    }

  }

}