# Filter out apks that have zero data flows
# Outputs two files:
# [1] "yesFlowApkList.txt": list of apks that contain a data flow
# Apks containing a vulnerable data flow will be sorted into a folder called 'good' and the FlowDroid output from them
# will be stored alongside them
# [2] "noFlowApkList.txt": list of apks that do not contain data flows
# These Apks will be sorted into a folder named "bad"

# Run script in the directory where the jars and config files are located
# Usage: python filter_apks.py <path to apks> <path to Android platforms> <use custom flowdroid: Y/N>
# Example: python filter_apks.py C:\Users\low-poly-Life\Documents\EC700\relevant C:\Users\low-poly-Life\AppData\Local\Android\sdk\platforms Y

import os
import sys
import shutil

apkDir = sys.argv[1]
platformDir = sys.argv[2]
use_custom_flowdroid = sys.argv[3]

if os.path.exists("soot-infoflow.jar"):
    os.remove("soot-infoflow.jar")

if use_custom_flowdroid[0].lower() == 'y':
	shutil.copyfile("soot-infoflow.jar.CUSTOM", "soot-infoflow.jar")
else:
	shutil.copyfile("soot-infoflow.jar.DEFAULT", "soot-infoflow.jar")

jars = "soot-trunk.jar;soot-infoflow.jar;soot-infoflow-android.jar;slf4j-api-1.7.5.jar;slf4j-simple-1.7.5.jar;axml-2.0.jar"

if os.path.exists("outputFile.txt"):
	os.remove("outputFile.txt")
if os.path.exists("scanned.txt"):
	os.remove("scanned.txt")
if os.path.exists("yesFlowApkList.txt"):
	os.remove("yesFlowApkList.txt")
if os.path.exists("noFlowApkList.txt"):
	os.remove("noFlowApkList.txt")

if not os.path.exists(apkDir + "/good/"):
	os.makedirs(apkDir + "/good")
if not os.path.exists(apkDir + "/bad/"):
	os.makedirs(apkDir + "/bad")

for file in os.listdir(sys.argv[1]):
	if file.endswith(".apk"):
		print("scanned: ", file)
		with open("scanned.txt", "a") as scannedfile:
			scannedfile.write(file + "\n")
		cmd = "java -cp " + jars + " soot.jimple.infoflow.android.TestApps.Test " + apkDir + "/" + file + " " + platformDir + " > outputFile.txt"
		print(cmd)
		os.system(cmd)
		if 'Found a flow to sink' in open("outputFile.txt").read():
			print("Dataflows found in: ", file)
			with open("yesFlowApkList.txt", "a") as yayfile:
				yayfile.write(file + "\n")
			if not os.path.exists(apkDir + "/good/" + file + "/"):
				os.makedirs(apkDir + "/good/" + file)
			os.rename(apkDir + "/" + file, apkDir + "/good/" + file + "/" + file)
			shutil.copyfile("outputFile.txt", apkDir + "/good/" + file + "/" + "outputFile.txt")
		else:
			print("No dataflows found in: ", file)
			with open("noFlowApkList.txt", "a") as nayFile:
				nayFile.write(file + "\n")
			os.rename(apkDir + "/" + file, apkDir + "/bad/" + file)

if os.path.exists("soot-infoflow.jar"):
    os.remove("soot-infoflow.jar")