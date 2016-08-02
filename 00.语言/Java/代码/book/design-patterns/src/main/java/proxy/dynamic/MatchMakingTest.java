package proxy.dynamic;

/**
 * Created by Yao on 2015/5/7.
 */
public class MatchMakingTest {

    PersonBeanImpl owner;
    PersonBeanImpl noOwner;

    public static void main(String[] args) {
        MatchMakingTest test= new MatchMakingTest();
        test.initializeData();
        test.drive();
    }

    private void initializeData(){
        owner= new PersonBeanImpl();
        owner.setName("owner");
        owner.setGender("A");
        owner.setInterests("AAAAAAAAAAA");
        noOwner= new PersonBeanImpl();
        noOwner.setName("noOwner");
        noOwner.setGender("B");
        noOwner.setInterests("BBBBBBBBBB");
    }

    private void drive(){
        IPersonBean ownerProxy= ProxyCenter.getOwnerProxy(owner);
        System.out.println(String.format("Name is %s", ownerProxy.getName()));
        ownerProxy.setInterests("New AAAAA");
        System.out.println(String.format("Interests is %s", ownerProxy.getInterests()));
        try{
            ownerProxy.setHotOrNotRating(100);
        }catch (Exception e){
            System.out.println("不允许通过个人代理更新投票数值");
        }

        IPersonBean noOwnerProxy= ProxyCenter.getNoownerProxy(owner);
        System.out.println(String.format("Name is %s", noOwnerProxy.getName()));
        noOwnerProxy.setHotOrNotRating(100);
        noOwnerProxy.setHotOrNotRating(50);
        System.out.println(String.format("Rating is %s", noOwnerProxy.getHotOrNotRating()));
        try{
            noOwnerProxy.setName("new name for owner");
        }catch (Exception e){
            System.out.println("无法更新非自身代理的相关姓名、等级和兴趣等属性");
        }
    }
}
