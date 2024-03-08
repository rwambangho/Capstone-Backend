package Capstone.Capstone.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemberRepository {

    private  static Map<Long,Member> store=new HashMap<>();
    private static AtomicLong sequence=new AtomicLong(0l);
    private static final MemberRepository instance= new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){}

    public Member save (Member member){

        long code= sequence.incrementAndGet();
        member.setCode(code);
        store.put(Long.valueOf(code), member);
        return member;
    }

    public Member findById(Long code){
        return store.get(code);

    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
