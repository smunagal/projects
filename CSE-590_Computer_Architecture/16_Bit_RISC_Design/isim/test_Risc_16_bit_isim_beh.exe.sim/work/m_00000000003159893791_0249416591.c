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
static const char *ng0 = "C:/Users/Shiva Reddy/Desktop/FinalProject-Proj2/Datapath_Unit.v";
static int ng1[] = {0, 0};
static const char *ng2 = "COUNTER INITIALIZED";
static const char *ng3 = "test\\test.prog";
static unsigned int ng4[] = {65535U, 65535U};
static int ng5[] = {1, 0};



static void Initial_16_0(char *t0)
{
    char *t1;
    char *t2;

LAB0:    xsi_set_current_line(17, ng0);

LAB2:    xsi_set_current_line(18, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 1768);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 4);
    xsi_set_current_line(19, ng0);
    xsi_vlogfile_write(1, 0, 0, ng2, 1, t0);

LAB1:    return;
}

static void Always_22_1(char *t0)
{
    char t5[8];
    char t21[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    unsigned int t16;
    unsigned int t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;

LAB0:    t1 = (t0 + 3416U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(22, ng0);
    t2 = (t0 + 3736);
    *((int *)t2) = 1;
    t3 = (t0 + 3448);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(22, ng0);

LAB5:    xsi_set_current_line(24, ng0);
    t4 = (t0 + 2088);
    xsi_vlogfile_readmemb(ng3, 0, t4, 0, 0, 0, 0);
    xsi_set_current_line(25, ng0);
    t2 = (t0 + 2088);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = (t0 + 2088);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 2088);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = (t0 + 2248);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    xsi_vlog_generic_get_array_select_value(t5, 16, t4, t8, t11, 2, 1, t14, 4, 2);
    t15 = (t0 + 1608);
    xsi_vlogvar_assign_value(t15, t5, 0, 0, 16);
    xsi_set_current_line(26, ng0);
    t2 = (t0 + 1608);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = ((char*)((ng4)));
    memset(t5, 0, 8);
    if (*((unsigned int *)t4) != *((unsigned int *)t6))
        goto LAB7;

LAB6:    t7 = (t4 + 4);
    t8 = (t6 + 4);
    if (*((unsigned int *)t7) != *((unsigned int *)t8))
        goto LAB7;

LAB8:    t9 = (t5 + 4);
    t16 = *((unsigned int *)t9);
    t17 = (~(t16));
    t18 = *((unsigned int *)t5);
    t19 = (t18 & t17);
    t20 = (t19 != 0);
    if (t20 > 0)
        goto LAB9;

LAB10:
LAB11:    goto LAB2;

LAB7:    *((unsigned int *)t5) = 1;
    goto LAB8;

LAB9:    xsi_set_current_line(26, ng0);

LAB12:    xsi_set_current_line(27, ng0);
    t10 = (t0 + 2248);
    t11 = (t10 + 56U);
    t12 = *((char **)t11);
    t13 = ((char*)((ng5)));
    memset(t21, 0, 8);
    xsi_vlog_unsigned_add(t21, 32, t12, 4, t13, 32);
    t14 = (t0 + 2248);
    xsi_vlogvar_assign_value(t14, t21, 0, 0, 4);
    xsi_set_current_line(28, ng0);
    t2 = (t0 + 1768);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = ((char*)((ng5)));
    memset(t5, 0, 8);
    xsi_vlog_unsigned_add(t5, 32, t4, 4, t6, 32);
    t7 = (t0 + 1768);
    xsi_vlogvar_assign_value(t7, t5, 0, 0, 4);
    goto LAB11;

}


extern void work_m_00000000003159893791_0249416591_init()
{
	static char *pe[] = {(void *)Initial_16_0,(void *)Always_22_1};
	xsi_register_didat("work_m_00000000003159893791_0249416591", "isim/test_Risc_16_bit_isim_beh.exe.sim/work/m_00000000003159893791_0249416591.didat");
	xsi_register_executes(pe);
}
