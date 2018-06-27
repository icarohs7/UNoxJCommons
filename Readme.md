# UNoxJCommons 
Biblioteca de componentes para a linguagem Java

## Sumário

* [Dependências](#dependências)
  * [Gradle](#gradle)
  * [Maven](#maven)
* [Swing](#swing)
    * [Tabelas:](#tabelas)
        * [Obter instância da tabela](#obter-instância-da-tabela)
        * [Tabela editavel](#tabela-editavel)
        * [Tabela a partir de um TableModel personalizado](#tabela-a-partir-de-um-tablemodel-personalizado)
        * [Tabela embrulhada em um JScrollPane](#tabela-embrulhada-em-um-jscrollpane)
        * [NXTableModel](#nxtablemodel)
        * [Manipulando dados da tabela](#manipulando-dados-da-tabela)
    * [NXField: ](#nxfield)
        * [Instanciando](#instanciando)
        * [Recuperando o texto](#recuperando-o-texto)
        * [Manipulando o texto](#manipulando-o-texto)
        * [Binding bidirecional](#binding-bidirecional)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>


## Dependências

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
# Swing

## Tabelas
Classes para simplificar a manipulações de tabelas em Swing

##### Obter instância da tabela

```java
String[] colunas = {"ID","Nome","Idade"};
String[][] dados = {
		{"1","Icaro","21"},
		{"2","Daniel","20"}
};

// Instância da tabela com os dados e colunas
JTable tabela = new NXTable(dados,colunas);
```

##### Tabela editavel

```java
JTable tabela = NXTable.ofMutableCells(dados,colunas);
```

##### Tabela a partir de um TableModel personalizado

```java
TableModel model = new DefaultTableModel(dados,colunas);

JTable tabela = NXTable.ofACustomModel(model);
```

##### Tabela embrulhada em um JScrollPane

```java
tabela.getScrollableTable();
```

### NXTableModel
Através desse table model é possível editar livremente as células da tabela.<br> 
Utilizado por padrão na chamada ScrollTable.ofMutableCells

```java
JTable tabela = NXTable.ofMutableCells(dados,colunas);
NXTableModel model = (NXTableModel) tabela.getModel();
```

##### Manipulando dados da tabela

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
model.removeRow(0); // Quando o primeiro elemento é removido, o seguinte toma seu lugar

// Editar linhas da tabela
model.setValueAt(new String[]{"4","Marcos","28"}, 3); // Altera a quarta linha

// Editar células da tabela
model.setValueAt("Marta",2,1); // Altera a célula na linha 3 e coluna 2

// Substituir todos os dados ta tabela
String[][] novosDados = {
		{"1","Railander","21"},
		{"2","Jefersom","22"}
};

model.setAllRows(novosDados); // Array bidimensional ou lista de arrays
```

## NXField
Classe composta de uma label e um text field associado, utilizada
para representar um campo de formulário ou entrada de dados genérica

##### Instanciando

```java
// Cria um campo associado a uma label
// com o texto "Nome"
NXField campo = new NXField("Nome");
```

##### Recuperando o texto
É possível recuperar o texto através do acesso direto ao JTextField,
mas o recomendado é utilizar a propriedade text dentro do próprio
NXField:

```java
// Recupera o texto do campo
String texto = campo.getText();
```

##### Manipulando o texto
Ao utilizar a propriedade de texto do campo, é possível alterar o
texto presente e utilizar binding para conectar o texto a uma propriedade

```java
// Editar o texto
campo.setText("Olá, Mundo!");

//Realizar binding do texto, interligando o valor do campo à propriedade desejada
StringProperty propriedade = new SimpleStringProperty();

campo.textProperty().bind(propriedade); // Atrela valor da propriedade ao valor do campo, unidirecionalmente

propriedade.setText("Olá, Mundo!"); // Quando a propriedade se altera, o valor do campo também é alterado
```

##### Binding bidirecional
Utilizado para conectar uma propriedade ao valor do campo bidirecionalmente,
de forma que alterações em qualquer uma das partes se reflete na outra

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