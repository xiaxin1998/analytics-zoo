# CMake generated Testfile for 
# Source directory: /home/xiaxin/analytics-zoo/cmake-3.12.1/Utilities/cmcurl
# Build directory: /home/xiaxin/analytics-zoo/cmake-3.12.1/Utilities/cmcurl
# 
# This file includes the relevant testing commands required for 
# testing this directory and lists subdirectories to be tested as well.
add_test(curl "LIBCURL" "http://open.cdash.org/user.php")
subdirs("lib")
