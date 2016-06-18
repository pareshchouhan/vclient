import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import spark.Spark;
/**
 * Created by hudel on 6/18/2016.
 */
public class ServerMain {
    public static void main(String[] args) {

        Spark.port(8080);
        System.out.println("Test");
        Spark.get("/", (request, response) -> {
            return "Hello world!";
        });
//        System.setProperty("jna.library.path","C:\\Program Files\\VirtViewer v3.1-256\\bin");
//        System.setProperty("jna.library.path","E:\\JavaProjects\\libs");
//        System.load("E:\\JavaProjects\\libs\\libvirt.dll");
        Connect conn=null;
        try{
            conn = new Connect("test:///default", true);
        } catch (LibvirtException e) {
            System.out.println("exception caught:"+e);
            System.out.println(e.getError());
        }
        try{
            Domain testDomain=conn.domainLookupByName("test");
            System.out.println("Domain:" + testDomain.getName() + " id " +
                    testDomain.getID() + " running " +
                    testDomain.getOSType());
        } catch (LibvirtException e) {
            System.out.println("exception caught:"+e);
            System.out.println(e.getError());
        }
        return;
    }
}
