# LISTENER

CREATE InputStream

CREATE DataInputStream

CREATE Socket

PUBLIC CONSTRUCT LISTENER (SOCKET connectionIn)
{
	
	STORE connectionIn
	
	InputStream = connectionIn.getInputStream
	
	DataInputStream = NEW object(InputStream)
}

PROTECTED Call
{

	DataInputStream.readUTF = msg
	STORE msg
}

# ChatClient

CREATE OutputStream

CREATE DataOutputStream

CREATE LISTENER

CREATE INTEGER PortNumber

CREATE STRING MachineIP

CREATE STRING Name

PUBLIC VOID Start{

	RUN getInfo

	ON KEY 'Enter' RELEASE{
	
		DataOutputStream.write
		
	}
	
	PRIVATE startClientThread{
	
	CREATE Socket connection 

	connection = new SOCKET (MachineIP, PortNumber)
	
	OutputStream = connection.getOutputStream
	
	DataOutputStream = new DataOutputStream (OutputStream)
	
	CREATE NEW Thread(LISTENER)
	
	START Thread

PUBLIC VOID getInfo{
	
	DISPLAY "Enter Remote Host"
	
	INPUT MachineIP
	
	DISPLAY "Enter PortNumber"
	
	INPUT PortNumber
	
	DISPLAY "Enter UsrName"
	
	INPUT Name
	
}

PUBLIC VOID Stop{

	SYSTEM EXIT
	
}

PUBLIC VOID Main{

	LAUNCH ARGUMENTS
	
}

# Generate Private/Public Key Pair

CREATE KeyPairGenerator WITH INSTANCE of "Algorithm" AND "Provider"

GENERATE SecureRandom WITH INSTANCE of "Algorithm" AND "Provider"

INITIALISE KeyPairGenerator WITH "Byte Size" AND "SecureRandom"

CREATE KeyPair with KeyPairGenerator

STORE KeyPair

# Contacts List 

READ KeyDirectory

SEND 'PING' to IP Range

RECIEVE application KEY

IF KEY MATCH KeyDirectory[i] THEN ASSIGN IP to Contact "KeyDirectory[i]"
