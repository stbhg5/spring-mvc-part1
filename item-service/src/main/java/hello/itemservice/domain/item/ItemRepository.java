package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static 사용
    // private static final Map<Long, Item> store = new ConcurrentHashMap<>(); // 동시성 문제로 ConcurrentHashMap 사용하기
    private static long sequence = 0L; // static 사용
    // private static AtomicLong sequence = new AtomicLong(0); // 동시성 문제로 AtomicLong 사용하기

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // ArrayList 로 감싼 이유 : ArrayList 값 추가해도 store 에 영향 없기 때문
    }

    // 프로젝트 규모가 커지면, ItemParamDto 같이 설계상 명확한 목적의 클래스를 만드는게 좋다.
    // 중복 vs 명확성 : 명확성 우선!!
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // 테스트 코드에서 사용
    public void clearStore() {
        store.clear();
    }

}