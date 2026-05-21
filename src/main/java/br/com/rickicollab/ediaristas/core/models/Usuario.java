package br.com.rickicollab.ediaristas.core.models;

import java.time.LocalDate;
import java.util.List;

import br.com.rickicollab.ediaristas.core.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    @ToString.Include
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    @ToString.Include
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "tipo_usuario", length = 8, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(nullable = true, unique = true, length = 11)
    private String cpf;

    @Column(nullable = true)
    private LocalDate nascimento;

    @Column(nullable = true, length = 11)
    private String telefone;

    @Column(nullable = true)
    private Double reputacao;

    @Column(name = "chave_pix", nullable = true, unique = true)
    private String chavePix;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "foto_documento", nullable = true)
    private Foto fotoDocumento;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "foto_usuario", nullable = true)
    private Foto fotoUsuario;

    @ManyToMany
    @JoinTable(
        name = "cidades_atendidas_usuarios",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "cidade_atendida_id")

    )
    private List<CidadeAtendida> cidadesAtendidas;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = true)
    private EnderecoDiarista endereco;


    public Boolean isDiarista(){
        return TipoUsuario.DIARISTA.equals(tipoUsuario);
    }

    public Boolean isCliente(){
        return TipoUsuario.CLIENTE.equals(tipoUsuario);
    }


}
