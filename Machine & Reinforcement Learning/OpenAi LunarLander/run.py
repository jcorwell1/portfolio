import gym
import numpy as np
from collections import deque
from agent import Agent
import pandas as pd
#######################DEFINE TRAINING PROCEDURE#######################################

def train(nEpisodes, maxT, epsStart, epsEnd, epsDecay):

    scores = []
    score100 = deque(maxlen=100)  # last 100 scores
    mean100 = []

    eps = epsStart
    for episode in range(1, nEpisodes + 1):

        state = env.reset()
        score = 0
        for t in range(maxT):
            action = agent.act(state, eps)
            next_state, reward, done, info = env.step(action)
            agent.store(state, action, reward, next_state, done)
            state = next_state
            score += reward
            if done:
                break
        ###save the score###
        score100.append(score)
        scores.append(score)

        ##decay epsilon
        eps = max(epsEnd, epsDecay * eps)

        ###check score
        print (str(episode) + '    ' + str(score))
        if episode % 100 == 0:
            print ('MEAN OF LAST 100 EPISODES:    ' + str(np.mean(score100)))
            mean100.append(np.mean(score100))

        if np.mean(score100) >= 200.0:
            print 'you did it!'
            mean100.append(np.mean(score100))
            break
    #return scores #if you want raw data
    return mean100 #if you want average score per 100 episodes
########################### LOAD ENVIRONMENT #############################
env = gym.make('LunarLander-v2')
env.seed(666)

######################## BASE CASE PARAMETERS #############################
nEpisodes = 2000
maxT = 1000
epsStart = 1.0
epsEnd = .01
epsDecay = .995
bufferSize = int(1e5)
batchSize = 64
gamma = .99
tau = .001
learnRate = 5e-4
TARGET_UPDATE = 4
stateSize = env.observation_space.shape[0]
actionSize = env.action_space.n
seed = 666

####################### RUN BASE CASE ##############################
agent = Agent(stateSize, actionSize, bufferSize, batchSize, gamma, tau, learnRate, TARGET_UPDATE)

Training = train(nEpisodes, maxT, epsStart, epsEnd, epsDecay)
df = pd.DataFrame({"episode": np.arange(len(Training)), "score": Training})
df.to_csv("data-BASE-MEAN100.csv", index=False)

'''
######################## RUN WITH VARIED PARAMETERS #####################################

gammaArr = [.2,.4,.6,.8,1]
learnRateArr = [.00005,.0005,.005,.05,.5]
batchSizeArr = [8,16,32,64,128]
epsilonDecayArr = [.2,.4,.6,.8,1]

###EXPERIMENT 1:::: VARY THE GAMMA
for i in gammaArr:

    gamma = i
    agent = Agent(stateSize,actionSize,bufferSize,batchSize,gamma,tau,learnRate,TARGET_UPDATE)

    Training = train(nEpisodes,maxT,epsStart,epsEnd,epsDecay)
    df = pd.DataFrame({"episode" : np.arange(len(Training)), "score" : Training})
    df.to_csv("data-GAMMA-" + str(i) + ".csv", index=False)
gamma = .99 #reset
###EXPERIMENT 2:::: VARY THE LR
for i in learnRateArr:

    learnRate = i
    agent = Agent(stateSize,actionSize,bufferSize,batchSize,gamma,tau,learnRate,TARGET_UPDATE)

    Training = train(nEpisodes,maxT,epsStart,epsEnd,epsDecay)
    df = pd.DataFrame({"episode" : np.arange(len(Training)), "score" : Training})
    df.to_csv("data-LR-" + str(i) + ".csv", index=False)
learnRate = 5e-4 #reset
###EXPERIMENT 3:::: VARY THE batchSize
for i in batchSizeArr:

    batchSize = i
    agent = Agent(stateSize,actionSize,bufferSize,batchSize,gamma,tau,learnRate,TARGET_UPDATE)

    Training = train(nEpisodes,maxT,epsStart,epsEnd,epsDecay)
    df = pd.DataFrame({"episode" : np.arange(len(Training)), "score" : Training})
    df.to_csv("data-BATCHSIZE-" + str(i) + ".csv", index=False)

batchSize = 64 #reset
##EXPERIMENT 4:::: VARY THE EPS DECAY RATE
for i in epsilonDecayArr:

    epsilonDecay = i
    agent = Agent(stateSize,actionSize,bufferSize,batchSize,gamma,tau,learnRate,TARGET_UPDATE)

    Training = train(nEpisodes,maxT,epsStart,epsEnd,epsDecay)
    df = pd.DataFrame({"episode" : np.arange(len(Training)), "score" : Training})
    df.to_csv("data-EPSDECAY-" + str(i) + ".csv", index=False)

epsDecay = .995 #reset
'''