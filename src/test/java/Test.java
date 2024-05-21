import org.eugeneb.User;
import org.testng.Assert;

public class Test {

    @org.testng.annotations.Test
    public void test1() {
        User user = new User(1, "eugene", "eugene@mail.com");
        System.out.println(user.getEmail());
        Assert.assertEquals(user.getEmail(), "eugene");
    }
}
