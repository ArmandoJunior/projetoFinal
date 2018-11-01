package br.com.container.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ {

	public static volatile SingularAttribute<Funcionario, Funcao> funcao;
	public static volatile SingularAttribute<Funcionario, Endereco> endereco;
	public static volatile SingularAttribute<Funcionario, String> nome;
	public static volatile SingularAttribute<Funcionario, Long> id;
	public static volatile SingularAttribute<Funcionario, String> email;

}

