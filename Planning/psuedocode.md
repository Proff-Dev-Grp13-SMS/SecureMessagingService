# Establish Connection With Alice

OPEN PORT  XXX on [Bob's Machine]

PING [Alice's IP]

IF !PING {

	OUTPUT "ALICE is offline"

}ELSE {

	HANDSHAKE alice

	CREATE PACKET

	SIGN DATA

	SEND PACKET	
}


RECIEVE ALICE PACKET

STORE ALICE KEY

# Send Message TO Alice
INPUT message

STORE message

ENCRYPT message WITH KEY

CREATE PACKET

ADD message TO PACKET

SEND PACKET

<<<<<<< Updated upstream
# Recieve Message FROM Alice
LISTEN on PORT XXX

RECIEVE PACKET
=======
PUBLIC VOID Start{

	RUN getInfo
>>>>>>> Stashed changes

STORE KEY

STORE message

DECRYPT message

OUTPUT message

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

STORE PrivateKey FROM KeyPair

<<<<<<< Updated upstream
STORE PublicKey FROM KeyPair
=======
IF KEY MATCH KeyDirectory[i] THEN ASSIGN IP to Contact "KeyDirectory[i]"
>>>>>>> Stashed changes
