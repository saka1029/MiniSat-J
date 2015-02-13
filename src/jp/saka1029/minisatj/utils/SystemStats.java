package jp.saka1029.minisatj.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;


///****************************************************************************************[System.h]
//Copyright (c) 2003-2006, Niklas Een, Niklas Sorensson
//Copyright (c) 2007-2010, Niklas Sorensson
//
//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
//associated documentation files (the "Software"), to deal in the Software without restriction,
//including without limitation the rights to use, copy, modify, merge, publish, distribute,
//sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all copies or
//substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
//NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
//OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//**************************************************************************************************/
//
//#ifndef Minisat_System_h
//#define Minisat_System_h
//
//#if defined(__linux__)
//#include <fpu_control.h>
//#endif
//
//#include "mtl/IntTypes.h"
//
////-------------------------------------------------------------------------------------------------
//
//namespace Minisat {
public class SystemStats {
//
//static inline double cpuTime(void); // CPU-time in seconds.
//extern double memUsed();            // Memory in mega bytes (returns 0 for unsupported architectures).
//extern double memUsedPeak();        // Peak-memory in mega bytes (returns 0 for unsupported architectures).
//
//}
//
////-------------------------------------------------------------------------------------------------
//// Implementation of inline functions:
//
//#if defined(_MSC_VER) || defined(__MINGW32__)
//#include <time.h>
//
//static inline double Minisat::cpuTime(void) { return (double)clock() / CLOCKS_PER_SEC; }
//
//#else
//#include <sys/time.h>
//#include <sys/resource.h>
//#include <unistd.h>
//
//static inline double Minisat::cpuTime(void) {
//    struct rusage ru;
//    getrusage(RUSAGE_SELF, &ru);
//    return (double)ru.ru_utime.tv_sec + (double)ru.ru_utime.tv_usec / 1000000; }
	public static double cpuTime() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		if (!bean.isCurrentThreadCpuTimeSupported())
			return 0;
		return (double)bean.getCurrentThreadUserTime() / 1000000000D;
	}
//
//	struct rusage {
//	    struct timeval ru_utime; /* �g�p���ꂽ���[�U�[���� */
//	    struct timeval ru_stime; /* �g�p���ꂽ�V�X�e������ */
//	    long   ru_maxrss;        /* RAM ��ɑ��݂��鉼�z�y�[�W�̃T�C�Y
//	                               (resident set size) �̍ő�l */
//	    long   ru_ixrss;         /* ���L�������̍��v�T�C�Y */
//	    long   ru_idrss;         /* �񋤗L�f�[�^�̍��v�T�C�Y */
//	    long   ru_isrss;         /* �񋤗L�X�^�b�N�̍��v�T�C�Y */
//	    long   ru_minflt;        /* ���p���ꂽ�y�[�W */
//	    long   ru_majflt;        /* �y�[�W�t�H�[���g */
//	    long   ru_nswap;         /* �X���b�v */
//	    long   ru_inblock;       /* �u���b�N���͑��� */
//	    long   ru_oublock;       /* �u���b�N�o�͑��� */
//	    long   ru_msgsnd;        /* ���M���ꂽ���b�Z�[�W */
//	    long   ru_msgrcv;        /* ��M���ꂽ���b�Z�[�W */
//	    long   ru_nsignals;      /* ��M���ꂽ�V�O�i�� */
//	    long   ru_nvcsw;         /* �Ӑ}�����R���e�L�X�g�؂�ւ� */
//	    long   ru_nivcsw;        /* �Ӑ}���Ȃ��R���e�L�X�g�؂�ւ� */
//	};
//	struct�@timeval�@{
//	�@�@long�@tv_sec; 			//	tv_sec �F �w�肷�鎞�Ԃ�1�b�ȏ�̕����i�b�P�ʁj
//	�@�@long�@tv_usec;			//	tv_usec �F �w�肷�鎞�Ԃ�1�b�����̕����i�}�C�N���b�P�ʁj
//	};
//#endif
//
//#endif

///***************************************************************************************[System.cc]
//Copyright (c) 2003-2006, Niklas Een, Niklas Sorensson
//Copyright (c) 2007-2010, Niklas Sorensson
//
//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
//associated documentation files (the "Software"), to deal in the Software without restriction,
//including without limitation the rights to use, copy, modify, merge, publish, distribute,
//sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all copies or
//substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
//NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
//OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//**************************************************************************************************/
//
//#include "utils/System.h"
//
//#if defined(__linux__)
//
//#include <stdio.h>
//#include <stdlib.h>
//
//using namespace Minisat;
//
//// TODO: split the memory reading functions into two: one for reading high-watermark of RSS, and
//// one for reading the current virtual memory size.
//
//static inline int memReadStat(int field)
//{
//    char  name[256];
//    pid_t pid = getpid();
//    int   value;
//
//    sprintf(name, "/proc/%d/statm", pid);
//    FILE* in = fopen(name, "rb");
//    if (in == NULL) return 0;
//
//    for (; field >= 0; field--)
//        if (fscanf(in, "%d", &value) != 1)
//            printf("ERROR! Failed to parse memory statistics from \"/proc\".\n"), exit(1);
//    fclose(in);
//    return value;
//}
//
//
//static inline int memReadPeak(void)
//{
//    char  name[256];
//    pid_t pid = getpid();
//
//    sprintf(name, "/proc/%d/status", pid);
//    FILE* in = fopen(name, "rb");
//    if (in == NULL) return 0;
//
//    // Find the correct line, beginning with "VmPeak:":
//    int peak_kb = 0;
//    while (!feof(in) && fscanf(in, "VmPeak: %d kB", &peak_kb) != 1)
//        while (!feof(in) && fgetc(in) != '\n')
//            ;
//    fclose(in);
//
//    return peak_kb;
//}
//
//double Minisat::memUsed() { return (double)memReadStat(0) * (double)getpagesize() / (1024*1024); }
	public static double memUsed() {
		return Runtime.getRuntime().totalMemory() / (1024 * 1024);
	}
//double Minisat::memUsedPeak() { 
//    double peak = memReadPeak() / 1024;
//    return peak == 0 ? memUsed() : peak; }
//
	public static double memUsedPeak() {
		// TODO: must implement
		return memUsed();
	}
//#elif defined(__FreeBSD__)
//
//double Minisat::memUsed(void) {
//    struct rusage ru;
//    getrusage(RUSAGE_SELF, &ru);
//    return (double)ru.ru_maxrss / 1024; }
//double MiniSat::memUsedPeak(void) { return memUsed(); }
//
//
//#elif defined(__APPLE__)
//#include <malloc/malloc.h>
//
//double Minisat::memUsed(void) {
//    malloc_statistics_t t;
//    malloc_zone_statistics(NULL, &t);
//    return (double)t.max_size_in_use / (1024*1024); }
//
//#else
//double Minisat::memUsed() { 
//    return 0; }
//#endif
}