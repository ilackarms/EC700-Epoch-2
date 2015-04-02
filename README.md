# EC700-Epoch-2 
This project contains the filtered set of potentially vulnerable apks and all of the sources necessary to run FlowDroid against them (either the modified FlowDroid or original).

To run flowdroid, open a shell in the flowdroid directory and run the following command:

python filter_apks.py <path to apks> <path to Android platforms> <Y/N>

Where y/n indicates whether to use the modified (yes) or original (no) version of FlowDroid. Custom version of flowdroid is unstable withsome APKs.

FlowDroidTestApp contains the AndroidStudio project that we used as a test app to debug and modify FlowDroid (as well as determine its capabilities).

custom-flowdroid-code contains the modified source code for FlowDroid. Each subfolder is an eclipse project; the only relevant project is soot-infoflow; most of the modified code can be found in com.soot.infoflow.problems.InfoFlowProblem (mostly one-line changes). To build, each folder must be loaded into eclipse as a separate file in the workspace, and all of the jars in the flowdroid folder must be imported as dependencies (if there are missing dependency errors in soot-infoflow-android). Export soot-infoflow as soot-infoflow.jar