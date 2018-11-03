import pandas as pd
import matplotlib.pyplot as plt

#base
df = pd.read_csv('data-BASE-.csv')
dfMEAN100 = pd.read_csv('data-BASE-MEAN100.csv')

#LR
df1 = pd.read_csv('data-LR-0.5.csv')
df2 = pd.read_csv('data-LR-0.05.csv')
df3 = pd.read_csv('data-LR-0.005.csv')
df4 = pd.read_csv('data-LR-0.0005.csv')
df5 = pd.read_csv('data-LR-5e-05.csv')

#gamma
df6 = pd.read_csv('data-GAMMA-0.2.csv')
df7 = pd.read_csv('data-GAMMA-0.4.csv')
df8 = pd.read_csv('data-GAMMA-0.6.csv')
df9 = pd.read_csv('data-GAMMA-0.8.csv')
df10 = pd.read_csv('data-GAMMA-1.csv')

#EPSDECAY
df11 = pd.read_csv('data-EPSDECAY-0.2.csv')
df12 = pd.read_csv('data-EPSDECAY-0.4.csv')
df13 = pd.read_csv('data-EPSDECAY-0.6.csv')
df14 = pd.read_csv('data-EPSDECAY-0.8.csv')
df15 = pd.read_csv('data-EPSDECAY-1.csv')

#EPSDECAY
df16 = pd.read_csv('data-BATCHSIZE-8.csv')
df17 = pd.read_csv('data-BATCHSIZE-16.csv')
df18 = pd.read_csv('data-BATCHSIZE-32.csv')
df19 = pd.read_csv('data-BATCHSIZE-64.csv')
df20 = pd.read_csv('data-BATCHSIZE-128.csv')

fig1 = plt.figure()
ax1 = fig1.add_subplot(111)
ax1.plot(df1['episode'], df1['score'],label = '0.5')
ax1.plot(df2['episode'], df2['score'],label = '0.05')
ax1.plot(df3['episode'], df3['score'],label = '0.005')
ax1.plot(df4['episode'], df4['score'],label = '0.0005')
ax1.plot(df5['episode'], df5['score'],label = '0.00005')
plt.axis([0,2000, -500, 500])
plt.title("Learning Rate")
plt.xlabel('Episode')
plt.ylabel('Score')
plt.legend()
plt.show()

fig2 = plt.figure()
ax1 = fig2.add_subplot(111)
ax1.plot(df6['episode'], df6['score'],label = '0.2')
ax1.plot(df7['episode'], df7['score'],label = '0.4')
ax1.plot(df8['episode'], df8['score'],label = '0.6')
ax1.plot(df9['episode'], df9['score'],label = '0.8')
ax1.plot(df10['episode'], df10['score'],label = '1')
plt.axis([0,2000, -500, 500])
plt.title("Gamma")
plt.xlabel('Episode')
plt.ylabel('Score')
plt.legend()
plt.show()

fig3 = plt.figure()
ax1 = fig3.add_subplot(111)
ax1.plot(df11['episode'], df11['score'],label = '0.2')
ax1.plot(df12['episode'], df12['score'],label = '0.4')
ax1.plot(df13['episode'], df13['score'],label = '0.6')
ax1.plot(df14['episode'], df14['score'],label = '0.8')
ax1.plot(df15['episode'], df15['score'],label = '1')
plt.axis([0,2000, -500, 500])
plt.title("Epsilon Decay")
plt.xlabel('Episode')
plt.ylabel('Score')
plt.legend()
plt.show()

fig4 = plt.figure()
ax1 = fig4.add_subplot(111)
ax1.plot(df16['episode'], df16['score'],label = '8')
ax1.plot(df17['episode'], df17['score'],label = '16')
ax1.plot(df18['episode'], df18['score'],label = '32')
ax1.plot(df19['episode'], df19['score'],label = '64')
ax1.plot(df20['episode'], df20['score'],label = '128')
plt.axis([0,2000, -500, 500])
plt.title("Batch Size")
plt.xlabel('Episode')
plt.ylabel('Score')
plt.legend()
plt.show()


fig5 = plt.figure()
ax1 = fig5.add_subplot(111)
ax1.plot(df['episode'], df['score'])

plt.axis([0,2000, -500, 500])
plt.title("Base")
plt.xlabel('Episode')
plt.ylabel('Score')
plt.legend()
plt.show()

fig6 = plt.figure()
ax1 = fig6.add_subplot(111)
ax1.plot(dfMEAN100['episode'], dfMEAN100['score'])

plt.axis([0,7, -500, 500])
plt.title("Mean 100 episodes")
plt.xlabel('Episode')
plt.ylabel('Score')
plt.legend()
plt.show()

