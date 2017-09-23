import java.util.HashMap;
import java.util.Map;

/**
 * Created by ijaehyeon on 2017. 7. 30..
 */

public class testMain {
    public static void main(String[] args){

        Map<PhoneNumber, String> m
                = new HashMap<PhoneNumber, String>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny");
        System.out.println(m.get(new PhoneNumber(707, 867, 5309)));

    }
}
