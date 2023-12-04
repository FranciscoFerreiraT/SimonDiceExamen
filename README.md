# DIAGRAMA DE FLUJO
graph TD

A[Inicio] --> B[Start/Reset]
B --> C
C[ColorEnviado + Colores] --> D[+Color]
D --> E
E["Colores"] --> F[WaitColoresPulsados]
F --> G
G[AumentarColores] --> H{BotonEnviadoPulsado}
H --> |NO| E
H --> |SÍ| I[enviarDatos]
I --> J{Colores==ColorEnviado}
J --> |NO| K[Perdiste]
K --> L[FIN]
J --> |SÍ| M[aumentarRonda]
M --> C


# DIAGRAMA DE ESTADO
stateDiagram
    [*] --> START
    START --> SECUENCIA : Iniciar
    SECUENCIA --> WAIT : 
    WAIT --> INPUT : 
    INPUT --> COMPROBAR : 
    COMPROBAR --> SECUENCIA : SÍ
    COMPROBAR --> PERDER : NO
    PERDER --> START : 
