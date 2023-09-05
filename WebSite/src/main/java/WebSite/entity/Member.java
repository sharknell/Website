package WebSite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseEntity {
    @Id
    @Column(length = 45, nullable = false)
    private String id; // 주요 식별자로 Long 타입 사용

    @Column(length = 45, nullable = false)
    private String pw;
    
    @Column(length = 45, nullable = false)
    private String name;
    
    @Id
    @Column(length = 45, nullable = false)
    private String email;

    @Column(length = 45, nullable = false)
    private String phoneNum;
}
