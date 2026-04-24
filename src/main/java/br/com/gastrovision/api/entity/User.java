package br.com.gastrovision.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    // unique = true garante que dois usuários não podem ter o mesmo e-mail
    @Column(nullable = false, unique = true)
    private String email;

    // login também deve ser único para identificar o usuário no sistema
    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Address address;

    // @Enumerated(EnumType.STRING) salva "OWNER" ou "CUSTOMER" no banco (legível)
    // sem isso, salvaria 0 ou 1 (EnumType.ORDINAL) — frágil se a ordem mudar
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    // @CreationTimestamp: Hibernate preenche automaticamente na inserção
    // updatable = false impede que o campo seja sobrescrito em updates
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // @UpdateTimestamp: Hibernate atualiza automaticamente em cada save()
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
