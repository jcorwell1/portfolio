import json
import numpy as np
import mdptoolbox
from mdptoolbox import example
from collections import OrderedDict


'''
The purpose of this code is to demonstrate that it requires real effort to make policy iteration an inefficient algorithm.
You can design your own Transitional Matrix and rewards matrix to play with the given code.
Method generate mdp exists if you desire to parse your final mdp into a JSON file.
'''

def gen_mdp(T, R, gamma=0.75):
    """Generate an MDP from the provided arrays and gamma value."""
    ### thanks to https://gist.github.com/james-fu/e67d1b875938caf2767ac2821be587d1... JSON creation is a true art
    act1 = {"id": 0, "transitions": []}
    act2 = {"id": 1, "transitions": []}
    names = ("id", "probability", "reward", "to")
    init_vals = (0, 0, 0, 0)
    ids = [i for i in range(30)]
    mdp = OrderedDict()
    mdp["gamma"] = gamma
    mdp["states"] = [{
        "id": id_num,
        "actions": [
            OrderedDict(sorted(act1.items(), key=lambda t: t[0])),
            OrderedDict(sorted(act2.items(), key=lambda t: t[0]))
        ]
    } for id_num in ids]
    for i in range(30):
        mdp["states"][i]["actions"][0]["transitions"] = [
            OrderedDict(zip(names, init_vals)) for _ in range(30)
        ]
        mdp["states"][i]["actions"][1]["transitions"] = [
            OrderedDict(zip(names, init_vals)) for _ in range(30)
        ]
        for j in range(30):
            mdp["states"][i]["actions"][0]["transitions"][j]["id"] = j
            mdp["states"][i]["actions"][1]["transitions"][j]["id"] = j
            mdp["states"][i]["actions"][0]["transitions"][j]["probability"] = T[0, i, j]
            mdp["states"][i]["actions"][1]["transitions"][j]["probability"] = T[1, i, j]
            mdp["states"][i]["actions"][0]["transitions"][j]["reward"] = R[0, i, j]
            mdp["states"][i]["actions"][1]["transitions"][j]["reward"] = R[1, i, j]
            mdp["states"][i]["actions"][0]["transitions"][j]["to"] = j
            mdp["states"][i]["actions"][1]["transitions"][j]["to"] = j

    ans = json.dumps(mdp, separators=(',', ':'))
    return ans


def gen(states=30, actions=2):
    """Generate arrays to feed to the MDP parser."""

    P = .001 * np.ones((actions, states, states))  # transition probability
    R = np.ones((actions, states, states))  # rewards
    
    P[0, 0, 0] = .971
    P[1, 29, 29] = .971
    for i in range(states - 1):
        P[0, i + 1, i] = .971
        P[1, i, i + 1] = .971

    #place couple isolated rewards
    R[0, 0, 1] = 10000
    R[1, 29, 29] = 1000000
    return P, R


if __name__ == '__main__':
    P, R = gen()
    mdp_str = gen_mdp(P, R)
    pi = mdptoolbox.mdp.PolicyIteration(P, R, .75)
    pi.run()
    print("How many iterations can we push Policy Iteration to....")
    print(pi.iter)

    print("Lets have a reference for a world of the same size initialized with random transitional probabilities and rewards")

    q,t = example.rand(30,2,.75)

    pi = mdptoolbox.mdp.PolicyIteration(q, t, .75)
    pi.run()
    print(pi.iter)


    print("JSON text of the MDP you created")
    print(mdp_str)