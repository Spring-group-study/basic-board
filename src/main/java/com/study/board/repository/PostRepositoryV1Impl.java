package com.study.board.repository;

import com.study.board.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryV1Impl implements PostRepositoryV1 {

    //db를 대신 할 인메모리 컬렉션리스트 생성
    private static final List<Post> db = new ArrayList<>();
    //해쉬맵으로 생성가능함, 그러나 이렇게 했을때는 id값을 엔티티에서 제외시키는게 맞고
    //db에서 input쿼리가 자주 호출된다면 ArrayList가, select 쿼리가 빈번하다면 haspMap이 시간복잡도 상 효율적이다.
    private final Map<Long, Post> db1 = new HashMap<>();
    //id값 자동증가를 위한 id값 대체 변수
    private Long inputId = 0l;

    /**
     * 컬렉션의 모든 인덱스값을 리턴
     * @return list
     */
    @Override
    public List<Post> findAll() {
        return db;
    }

    /**
     * 생성한 리스트에서 stream.filter로 매개변수로 받아온 id값과 일치하는 값을 가져온다
     * findFirst()와 findAny()를 사용할수있는데, 차이점은
     * findFirst()는 일치하는 요소 중 stream에서 순서가 가장 빠른(인덱스넘버가 가장 작은)값을 리턴
     * findAny()는 가장 먼저 탐색되는 요소를 리턴한다
     * 직렬 배열에서는 같은값이 리턴되지만 parallel 배열에서는 findAny()에서 다른 값이 리턴될 수 있다.
     * @param id
     * @return
     */
    @Override
    public Post findById(Long id) {
        Post post = db.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().get();
        return post;
    }

    /**
     * post를 매개변수로 가져온 후, 생성한 리스트에 값이 없다면 id값을 1번을 최초로 부여하고
     * 그 다음 부터는 1씩 증가하여 id값을 부여한다.
     * @param post
     */
    @Override
    public void save(Post post) {
        if (db.isEmpty()) {
            inputId = 1l;
        } else if (db.size() != 0) {
            inputId = inputId + 1l;
        }
        Post savePost = new Post(inputId, post.getAuthor(), post.getTitle(), post.getContent());
        db.add(savePost);
    }


    /**
     * 파라미터로 받아온 id값으로 stream.filter를 적용해 리스트의 id값과 일치하는 데이터를 찾아
     * 나머지 파라미터 값으로 기존 데이터를 변경
     * @param id
     * @param author
     * @param content
     * @param title
     */
    @Override
    public void update(Long id, String author, String content, String title) {
        Post post = db.stream().filter(i -> i.getId().equals(id))
                .findFirst().get();
        post.toEntity(author, title, content);

    }

    /**
     * 파라미터로 id값을 가져와 filter로 해당 데이터를 찾고,
     * ifPresent()로 조건을 걸어 데이터가 있다면 그 데이터를 삭제
     * remove()는 collection의 기본 제공 메서드
     * @param id
     */
    @Override
    public void delete(Long id) {
        db.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().ifPresent(db::remove);
    }
}
