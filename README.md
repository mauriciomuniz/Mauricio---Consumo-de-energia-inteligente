#### Mauricio Santana Muniz
#     **Consumo de energia inteligente**

#### 1. Introdução
Considerando as vantagens que a _IoT_ pode proporcionar às pessoas e meio ambiente, o problema proposto foi um sistema automatizado de coleta de dados de energia elétrica de residências e tendo um medidor simulado em código para enviar esses dados. Nessa simulação temos duas aplicações que se comunicam em rede remotamente e também online para realizar cálculos, consultas e realizar todas as funções especificadas para o problema.

#### 2. Fundamentação teorica

Nesta sessão será descrita a base teórica necessária para a resolução do problema.

##### 2.1 API Rest
Segundo a Amazon[1], API significa _Application Programming Interface_ (Interface de Programação de Aplicação). No contexto de _APIs_, a palavra Aplicação refere-se a qualquer software com uma função distinta. A interface pode ser pensada como um contrato de serviço entre duas aplicações. Esse contrato define como as duas se comunicam usando solicitações e respostas. A documentação de suas respectivas APIs contém informações sobre como os desenvolvedores devem estruturar essas solicitações e respostas.  

A arquitetura da _API_ geralmente é explicada em termos de cliente e servidor. A aplicação que envia a solicitação é chamada de cliente e a aplicação que envia a resposta é chamada de servidor. Então, no exemplo do clima, o banco de dados meteorológico do instituto é o servidor e o aplicativo móvel é o cliente. 

Existem quatro maneiras diferentes pelas quais as APIs podem funcionar, dependendo de quando e por que elas foram criadas. Nesse problema foi usado _API Rest_. 

##### 2.1.1 API Rest

O termo REST significa _Representational State Transfer_, ou em português, Transferência Representacional de Estado. REST define um conjunto de funções como _GET, PUT, DELETE_ e assim por diante, que os clientes podem usar para acessar os dados do servidor. Clientes e servidores trocam dados usando _HTTP_.

A principal característica da _API REST_ é a ausência de estado. A ausência de estado significa que os servidores não salvam dados do cliente entre as solicitações. As solicitações do cliente ao servidor são semelhantes aos _URLs_ que você digita no navegador para visitar um site. A resposta do servidor corresponde a dados simples, sem a renderização gráfica típica de uma página da _Web_.[1]

#### 2.2 Protocolo TCP

TCP/IP é um acrônimo para o termo _Transmission Control Protocol/Internet Protocol Suite_, dois dos mais importantes protocolos que conformam a pilha de protocolos usados na Internet. O protocolo IP, base da estrutura de comunicação da Internet é um protocolo baseado no paradigma de chaveamento de pacotes (_packet-switching_).[2]

Os protocolos TCP/IP podem ser utilizados sobre qualquer estrutura de rede, seja ela simples como uma ligação ponto-a-ponto ou uma rede de pacotes complexa. Como exemplo, pode-se empregar estruturas de rede como _Ethernet, Token-Ring, FDDI, PPP, ATM, X.25, Frame-Relay,_ barramentos SCSI, enlaces de satélite, ligações telefônicas discadas e várias outras.

A arquitetura _TCP/IP_, assim como a _OSI_, realiza a divisão de funções do sistema de comunicação em estruturas de camadas.[2]

#### 2.3 Sockets 

De acordo com a [3]: “Socket é um ponto de comunicação entre duas máquinas”, ou seja, podemos enviar mensagens entre a máquina A e a máquina B através de uma conexão estabelecida com o _Socket_.

Para que essa comunicação seja possível, nós precisamos criar a classe Servidora que é responsável por esperar a conexão do cliente e a classe Cliente que irá conectar-se ao Servidor. Antes de iniciarmos a prática e começar a criar nosso ambiente de comunicação, vamos entender as classes que usaremos e seus métodos.

#### 2.4 Threads

Segundo [4] _Threads_ são unidades básicas de processamento em um sistema operacional. Elas representam um fluxo independente de execução dentro de um processo e permitem que várias tarefas sejam executadas simultaneamente dentro de um programa.

As _threads_ compartilham recursos como memória, arquivos abertos e outras informações com o processo pai. Elas são úteis para a execução de tarefas que podem ser paralelizadas, como processamento de imagem, operações de entrada e saída de dados em rede, cálculos intensivos e outras tarefas que podem ser executadas em segundo plano enquanto outras partes do programa continuam a ser executadas.

#### 2.5 Docker

_Docker_ é uma plataforma de software que fornece uma maneira de criar, implantar e executar aplicativos em contêineres. Contêineres são uma forma de virtualização em que um aplicativo é empacotado com todas as suas dependências e é executado como um processo isolado em um sistema operacional hospedeiro compartilhado. Isso torna mais fácil para os desenvolvedores criar aplicativos portáteis que possam ser executados em qualquer lugar, independentemente do sistema operacional ou do _hardware_ subjacente.[5]

#### 3. Resultados e discussões
Para desenvolver esse sistema foi utilizado a linguagem Java, incluindo suas bibliotecas nativas da linguagem, não foi utilizado nenhum _framework_, foi utilizado de _sockets_ para fazer a comunicação em rede. 


Nesse sistema foi feito uma aplicação responsável por cadastrar usuário, assim como atualizar seus dados. Já na outra faz suas consultas de monitorar o consumo de energia; consultar o consumo de cada cliente; gerar fatura a ser paga, assim também como um histórico de todas as faturas para cada cliente; alertar consumo de energia com consumo excessivo ou grande variação de energia.

O sistema a principio iria armazenar seus dados em um arquivo _Json_, mas devido ao tempo gasto em algumas etapas de desenvolvimento, essa idéia não foi adiante. Pórem boa parte dela ainda segue no código para possíveis melhorias.

Desenvolver aplicações que se comunicam em rede local ou internet é hoje uma necessidade crescente. Neste problema aprendemos a desenvolver este tipo de aplicação usando Java e sockets. Os sockets em Java representam um recurso poderoso para desenvolvimento de aplicações que podem comunicar-se via rede. Apesar de existirem frameworks que facilitam o desenvolvimento de aplicações em rede com Java é importante entender o fundamento da comunicação com sockets que é a base para toda e qualquer aplicação que utiliza comunicação em rede.


#### 4. Referências

[1] [Whats is an API?](https://aws.amazon.com/what-is/api/?nc1=h_ls). Disponivel em: <https://aws.amazon.com/what-is/api/?nc1=h_ls> Acesso em 21 de março de 2023.

[2] [Introdução à Arquitetura TCP/IP da Internet](https://www.gta.ufrj.br/grad/03_1/ip-security/paginas/introducao.html). Disponivel em: <https://www.gta.ufrj.br/grad/03_1/ip-security/paginas/introducao.html> Acesso em 21 de março de 2023

[3] [Lesson 1: Socket Communications](https://www.oracle.com/java/technologies/jpl2-socket-communication.html). Disponivel em: <https://www.oracle.com/java/technologies/jpl2-socket-communication.html> Acesso em 21 de março de 2023

[4] [Processes and Threads](https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html). Disponivel em: <https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html> Acesso em 21 de março de 2023

[5] [Docker: desenvolvimento de aplicações em containers](https://www.redhat.com/pt-br/topics/containers/what-is-docker). Disponivel em: <https://www.redhat.com/pt-br/topics/containers/what-is-docker> Acesso em 21 de março de 2023





