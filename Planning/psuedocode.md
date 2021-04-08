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

# Send Message

TO BE REWROTE

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

IF KEY MATCH KeyDirectory[i] THEN ASSIGN IP to Contact KeyDirectory[i]
