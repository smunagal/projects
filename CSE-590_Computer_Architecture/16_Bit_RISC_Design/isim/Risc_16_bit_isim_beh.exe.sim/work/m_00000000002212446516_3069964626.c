/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                       */
/*  \   \        Copyright (c) 2003-2009 Xilinx, Inc.                */
/*  /   /          All Right Reserved.                                 */
/* /---/   /\                                                         */
/* \   \  /  \                                                      */
/*  \___\/\___\                                                    */
/***********************************************************************/

/* This file is designed for use with ISim build 0x7708f090 */

#define XSI_HIDE_SYMBOL_SPEC true
#include "xsi.h"
#include <memory.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
static const char *ng0 = "C:/Users/Shiva Reddy/Desktop/FinalProject-Proj2/Risc_16_bit.v";
static const char *ng1 = "Inside Risc_16_bit";



static void Initial_26_0(char *t0)
{

LAB0:    xsi_set_current_line(27, ng0);

LAB2:    xsi_set_current_line(28, ng0);
    xsi_vlogfile_write(1, 0, 0, ng1, 1, t0);

LAB1:    return;
}


extern void work_m_00000000002212446516_3069964626_init()
{
	static char *pe[] = {(void *)Initial_26_0};
	xsi_register_didat("work_m_00000000002212446516_3069964626", "isim/Risc_16_bit_isim_beh.exe.sim/work/m_00000000002212446516_3069964626.didat");
	xsi_register_executes(pe);
}
