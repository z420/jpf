         L   K       ��������J�.�1)͵ L�Q:����$            ufor i in `ipcs | grep miallen | awk '{ print $2 }'`; do ipcrm sem $i; done
