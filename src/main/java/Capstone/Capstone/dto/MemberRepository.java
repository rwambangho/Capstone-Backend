package Capstone.Capstone.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemberRepository {

    private  static Map<String,Member> store=new HashMap<>();
    private static final MemberRepository instance= new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){}

    public Member save (Member member){

        store.put(member.getId(), member);
        return member;
    }

    public Member findById(String id){
        return store.get(id);

    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
