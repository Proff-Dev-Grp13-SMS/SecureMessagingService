# Establish Connection With Alice

TO BE REWROTE

# Send Message TO Alice

TO BE REWROTE

# Recieve Message FROM Alice

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
