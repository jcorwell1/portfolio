import numpy as np
import random
import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
from collections import namedtuple, deque
torch.manual_seed(666)
random.seed(666)

class Agent():


    def __init__(self, nStates, nActions, bufferSize, batchSize, gamma, tau, lr, updateEvery):

        self.nStates = nStates
        self.nActions = nActions
        self.updateEvery = updateEvery
        self.tau = tau
        self.batchSize = batchSize
        self.gamma = gamma
        self.qnetwork = QNN(nStates, nActions)
        self.qnetwork_target = QNN(nStates, nActions)
        self.optimizer = optim.Adam(self.qnetwork.parameters(), lr=lr)
        self.memory = ReplayBuffer(nActions, bufferSize, batchSize)
        self.t_step = 0

    def store(self, state, action, reward, next_state, done):
        # Save experience in memory
        self.memory.add(state, action, reward, next_state, done)

        self.t_step = (self.t_step + 1) % self.UPDATE_EVERY
        if self.t_step == 0:
            if len(self.memory) > self.BATCH_SIZE:
                experiences = self.memory.sample()
                self.learn(experiences, self.GAMMA)

    def act(self, state,eps):
        #Returns actions for given state as per current policy.

        state = torch.Tensor(state).float().unsqueeze(0)

        self.qnetwork_local.eval()

        with torch.no_grad(): #temporarily set all the requires_grad flag to false

            action_values = self.qnetwork_local(state)
        self.qnetwork_local.train()

        return self.epsilonGreedy(action_values,eps)
    def learn(self, experiences, gamma):
        
        #update network from past experiences
        states, actions, rewards, next_states, dones = experiences

        # Get max predicted Q values (for next states) from target model
        Q_targets_next = self.qnetwork_target(next_states).detach().max(1)[0].unsqueeze(1)
        # Q targets for current states
        Q_targets = rewards + (gamma * Q_targets_next * (1 - dones))
        # Get expected Q values from local model
        Q_expected = self.qnetwork(states).gather(1, actions)

        # loss
        self.computeLossUpdateWeights(Q_expected, Q_targets)
        # update target network
        self.soft_update(self.qnetwork, self.qnetwork_target, self.tau)
        
    def soft_update(self, network, networkTarget, tau):

        # QT_weights = tau*Q_weightsLocal + (1-tau)*QT_weights

        for target_param, local_param in zip(networkTarget.parameters(), network.parameters()):
            target_param.data.copy_(tau*local_param.data + (1.0-tau)*target_param.data)

    def computeLossUpdateWeights(self,Q_expected,Q_targets):
        # Compute loss
        loss = F.mse_loss(Q_expected, Q_targets)
        # updateWeights
        self.optimizer.zero_grad()
        loss.backward()
        self.optimizer.step()
    def epsilonGreedy(self,action_values, eps):
        # Epsilon-greedy action selection
        if random.random() > eps:
            return np.argmax(action_values.cpu().data.numpy())
        else:
            return random.choice(np.arange(self.action_size))

class ReplayBuffer:

    def __init__(self, nActions, bufferSize, batchSize):
        self.action_size = nActions
        self.memory = deque(maxlen=bufferSize)
        self.batch_size = batchSize
        self.experience = namedtuple("Experience", field_names=["state", "action", "reward", "next_state", "done"])

    def add(self, state, action, reward, next_state, done):
        #store an experience in your memory
        self.memory.append(self.experience(state, action, reward, next_state, done))

    def sample(self):
        #recall random memory from batch
        mem = random.sample(self.memory, self.batch_size)

        #get states, actions, rewards, next states, and done from past memory
        states = [e.state for e in mem]
        actions = [e.action for e in mem]
        rewards = [e.reward for e in mem]
        next_states =[e.next_state for e in mem]
        dones = [e.done for e in mem]

        #convert to tensors
        states = torch.Tensor(np.vstack(states))
        actions = torch.Tensor(np.vstack(actions)).long()
        rewards = torch.Tensor(np.vstack(rewards))
        next_states = torch.Tensor(np.vstack(next_states))
        dones = torch.Tensor(np.vstack(np.uint8(dones)))

        return (states, actions, rewards, next_states, dones)

    def __len__(self):
        return len(self.memory)

class QNN(nn.Module):
    #define a basic neural network
    #https://pytorch.org/tutorials/beginner/blitz/neural_networks_tutorial.html
    #(only linear)
    def __init__(self, state_size, action_size):

        super(QNN, self).__init__()

        self.fc1 = nn.Linear(state_size, 64)
        self.fc2 = nn.Linear(64, 64)
        self.fc3 = nn.Linear(64, action_size)

    def forward(self, state):
        x = F.relu(self.fc1(state))
        x = F.relu(self.fc2(x))
        return self.fc3(x)
