'''This code serves as an example of solving TD(lambda) solutions analytically
The specific mdp is 'mdp.png'

For this example we assume alpha and gamma = 1 for ease of modeling.
'''


#example 1
#V = [0.0, 4.0, 25.7, 0.0, 20.1, 12.2, 0.0]
#r = [7.9,-5.1,2.5,-7.2,9.0,0.0,1.6]
#probToState=0.81
# example 2
#V = [0.0,-5.2,0.0,25.4,10.6,9.2,12.3]
#r=[-2.4,0.8,4.0,2.5,8.6,-6.4,6.1]
#probToState=0.22
#hw problem giving wrong answe
#probToState = 0.64
#V=[0.0,4.9,7.8,-2.3,25.5,-10.2,-6.5]
#r=[-2.4,9.6,-7.8,0.1,3.4,-2.1,7.9]
#another one
#probToState = 0.76
#V=[0.0,9.3,0.0,12.8,0.0,22.4,3.6]
#r=[9.0,0.0,0.4,8.9,6.7,0.6,1.9]
##probToState=0.76
#V=[0.0,9.3,0.0,12.8,0.0,22.4,3.6]
#r=[9.0,0.0,0.4,8.9,6.7,0.6,1.9]
#probToState=0.55
#V = [0.0,0.0,5.6,9.4,0.0,0.0,-3.6]
#r=[2.4,4.2,-2.6,5.7,4.1,-2.3,0.9]
#probToState=0.59
#V=[0.0,10.7,-0.5,17.7,19.7,0.0,3.6]
#r=[2.8,-4.1,9.3,0.0,5.2,5.6,-2.8]
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
