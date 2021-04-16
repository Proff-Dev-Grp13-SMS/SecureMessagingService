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
		
		INPUT text
		
		DataOutputStream.write text
		
	}

}
	
PRIVATE startClientThread{
	
	CREATE Socket connection 

	connection = new SOCKET (MachineIP, PortNumber)
	
	OutputStream = connection.getOutputStream
	
	DataOutputStream = new DataOutputStream (OutputStream)
	
	CREATE NEW Thread(LISTENER)
	
	START Thread

}

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

# ServerClient

CREATE OutputStream

CREATE DataOutputStream

CREATE LISTENER

CREATE FINAL INTEGER PortNumber = XXXX

CREATE STRING Name

PUBLIC VOID Start{

	RUN getInfo
	
	RUN StartServerThread

	ON KEY 'Enter' RELEASE{
		
		INPUT text
		
		DataOutputStream.write text
		
	}

}

PRIVATE startServerThread{
	
	CREATE Socket connection 
	
	CREATE ServerSocket listenSocket
	
	INIT listenSocket WITH PortNumber
	
	INIT connection WITH listenSocket.accept
	
	OutputStream = connection.getOutputStream
	
	DataOutputStream = new DataOutputStream (OutputStream)
	
	INIT LISTENER WITH Connection
	
	CREATE NEW Thread(LISTENER)
	
	START Thread

}

PRIVATE VOID getInfo{

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

# Export Keys

CREATE File

SERIALIZE KeyPair

STORE KeyPair in File
