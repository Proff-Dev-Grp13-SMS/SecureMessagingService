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

# Recieve Message FROM Alice
LISTEN on PORT XXX

RECIEVE PACKET

STORE KEY

STORE message

DECRYPT message

OUTPUT message

# Generate Private/Public Key Pair

CREATE KeyPairGenerator WITH INSTANCE of "Algorithm" AND "Provider"

GENERATE SecureRandom WITH INSTANCE of "Algorithm" AND "Provider"

INITIALISE KeyPairGenerator WITH "Byte Size" AND "SecureRandom"

CREATE KeyPair with KeyPairGenerator

STORE PrivateKey FROM KeyPair

STORE PublicKey FROM KeyPair
