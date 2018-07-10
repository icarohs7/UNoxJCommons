# <img src ="https://github.com/icarohs7/UNoxJCommons/blob/master/assets/java-logo.png" width=24> UNoxJCommons
[![GitHub version](https://badge.fury.io/gh/icarohs7%2FUNoxJCommons.svg)](https://github.com/icarohs7/UNoxJCommons/releases)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.icarohs7/unoxjcommons/badge.svg)](https://mvnrepository.com/artifact/com.github.icarohs7/unoxjcommons)
[![GitHub license](https://img.shields.io/github/license/icarohs7/UNoxJCommons.svg)](https://github.com/icarohs7/UNoxJCommons/blob/master/LICENSE)

## Descrição

Biblioteca de componentes para a linguagem Java

# Sumário

* [Dependências](#dependências)
	+ [Gradle](#gradle)
	+ [Maven](#maven)
* [Funcionalidades](#funcionalidades)
	+ [NXTable](#nxtable)
	+ [NXTableModel](#nxtablemodel)
	+ [NXField](#nxfield)

# Dependências

#### Gradle

```
dependencies {
	// ...
	implementation 'com.github.icarohs7:unoxjcommons:<version>'
}

repositories {
	mavenCentral()
}
```

#### Maven

```
<dependencies>
	<!-- ... -->
	<dependency>
		<groupId>com.github.icarohs7</groupId>
		<artifactId>unoxjcommons</artifactId>
		<version>version</version>
	</dependency>
</dependencies>
```

# Funcionalidades

## NXTable
Implementação de `JTable` buscando simplificar a manipulação de tabelas em Swing

* Obter instância da tabela
```java
String[] colunas = {"ID","Nome","Idade"};
String[][] dados = {
		{"1","Icaro","21"},
		{"2","Daniel","20"}
};

// Instância da tabela com os dados e colunas
JTable tabela = new NXTable(dados,colunas);
```

* Tabela editavel
```java
JTable tabela = NXTable.ofMutableCells(dados,colunas);
```

* Tabela a partir de um `TableModel` personalizado
```java
TableModel model = new DefaultTableModel(dados,colunas);

JTable tabela = NXTable.ofACustomModel(model);
```

* Tabela embrulhada em um `JScrollPane`
```java
tabela.getScrollableTable();
```

## NXTableModel
Através desse table model é possível editar livremente as células da tabela.<br> 
Utilizado por padrão na chamada `ScrollTable.ofMutableCells`

```java
JTable tabela = NXTable.ofMutableCells(dados,colunas);
NXTableModel model = (NXTableModel) tabela.getModel();
```

* Manipulando dados da tabela
```java
// Criar tabela e obter model editável
JTable tabela = NXTable.ofMutableCells(dados,colunas);
NXTableModel model = (NXTableModel) tabela.getModel();

// Adicionar linhas à tabela
model.addRow(new String[]{"3","Maria","30"});
model.addRow(new String[]{"4","João","32"});
model.addRow(new String[]{"5","Geraldo","25"});

// Remover linhas da tabela
model.removeRow(0);

// Quando o primeiro elemento é removido, o seguinte toma seu lugar
model.removeRow(0);

// Editar linhas da tabela
model.setValueAt(new String[]{"4","Marcos","28"}, 3); // Altera a quarta linha

// Editar células da tabela
model.setValueAt("Marta",2,1); // Altera a célula na linha 3 e coluna 2

// Substituir todos os dados ta tabela
String[][] novosDados = {
		{"1","Railander","21"},
		{"2","Jefersom","22"}
};

// Array bidimensional ou lista de arrays
model.setAllRows(novosDados);
```

## NXField
Classe composta de uma `JLabel` e um `JTextField` associados.<br> 
Utilizada para representar um campo de formulário ou entrada de dados genérica

* Instanciando
```java
// Cria um campo associado a uma label
// com o texto "Nome"
NXField campo = new NXField("Nome");
```

* Recuperando o texto
```java
// Recupera o texto do campo
String texto = campo.getText();
```

* Manipulando o texto e realizando binding
```java
// Editar o texto
campo.setText("Olá, Mundo!");

//Realizar binding do texto, interligando o valor do campo à propriedade desejada
StringProperty propriedade = new SimpleStringProperty();

// Atrela valor da propriedade ao valor do campo, unidirecionalmente
campo.textProperty().bind(propriedade);

// Quando a propriedade se altera, o valor do campo também é alterado
propriedade.setText("Olá, Mundo!");
```

* Binding bidirecional
```java
// Instânciar a propriedade a ser atrelada ao campo
StringProperty propriedade = new SimpleStringProperty();

// Instânciar campo
NXField campo = new NXField("Nome:");

// Realizar o binding
campo.textProperty().bindBidirectional(propriedade);

// Alterações na propriedade são refletidas no campo
propriedade.set("Olá, Mundo!");

// Alterações no campo também são refletidas na propriedade
campo.setText("Alterações refletidas");
```