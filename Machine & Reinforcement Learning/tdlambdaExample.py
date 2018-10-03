'''This code serves as an example of solving TD(lambda) solutions analytically
The specific mdp is 'mdp.png'

For this example we assume alpha = 1 and gamma = 1 for ease of modeling.
'''


probToState = 0.5
V = [0, 3, 8, 2, 1, 2, 0]
r = [0, 0, 0, 4, 1, 1, 1]


#gamma and alpha equals one so the following equations are more simple
TDtop = r[0]+r[2]+r[4]+r[5]+r[6]
TDbottom = r[1]+r[3]+r[4]+r[5]+r[6]
TDoneTotal = probToState*TDtop + (1-probToState)*TDbottom

#path 1 mdp
EoneA = (V[1] + r[0])
EtwoA = (V[3] + r[0]+r[2])
EthreeA = V[4] + r[0] + r[2] + r[4]
EfourA = V[5] + r[0] + r[2] + r[4] + r[5]
EfiveA = V[6] + r[0] + r[2] + r[4] + r[5] + r[6]
EsixA =  r[0] + r[2] + r[4] + r[5] + r[6]

#path 2 mdp
EoneB = V[2] + r[1]
EtwoB = (V[3] + r[1]+r[3])
EthreeB = V[4] + r[1] + r[3] + r[4]
EfourB = V[5] + r[1] + r[3] + r[4] + r[5]
EfiveB = V[6] + r[1] + r[3] + r[4] + r[5] + r[6]
EsixB =  r[1] + r[3] + r[4] + r[5] + r[6]

Eone = probToState*EoneA+(1-probToState)*EoneB
Etwo = probToState*EtwoA+(1-probToState)*EtwoB
Ethree = probToState*EthreeA+(1-probToState)*EthreeB
Efour = probToState*EfourA+(1-probToState)*EfourB
Efive = probToState*EfiveA+(1-probToState)*EfiveB
Esix = probToState*EsixA+(1-probToState)*EsixB

eq1 = ("(1-x)["+str(EoneA)+'+ x*'+str(EtwoA)+'+x^2*'+str(EthreeA)+'+x^3*'+str(EfourA)+'+x^4*'+str(EfiveA)+']')

eq2 = ("(1-x)["+str(EoneB)+'+ x*'+str(EtwoB)+'+x^2*'+str(EthreeB)+'+x^3*'+str(EfourB)+'+x^4*'+str(EfiveB)+']')

FullEquation = str(probToState)+eq1 +' + '+ str(1-probToState)+eq2 + ' = ' +str(TDoneTotal)


alt = ("(1-x)["+str(Eone)+'+ x*'+str(Etwo)+'+x^2*'+str(Ethree)+'+x^3*'+str(Efour)+'+x^4*'+str(Efive)+'+x^5*'+str(Esix)+'] =  '+str(TDoneTotal))


print(alt)

print('You can plug this polynomial into WolframAlpha to solve for x')
