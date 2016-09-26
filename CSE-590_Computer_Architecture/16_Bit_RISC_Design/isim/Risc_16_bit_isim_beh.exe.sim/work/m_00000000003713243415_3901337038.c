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
static const char *ng0 = "time = %d\n";
static const char *ng1 = "\tmemory[0] = %b\n";
static int ng2[] = {0, 0};
static const char *ng3 = "\tmemory[1] = %b\n";
static int ng4[] = {1, 0};
static const char *ng5 = "\tmemory[2] = %b\n";
static int ng6[] = {2, 0};
static const char *ng7 = "\tmemory[3] = %b\n";
static int ng8[] = {3, 0};
static const char *ng9 = "\tmemory[4] = %b\n";
static int ng10[] = {4, 0};
static const char *ng11 = "\tmemory[5] = %b\n";
static int ng12[] = {5, 0};
static const char *ng13 = "\tmemory[6] = %b\n";
static int ng14[] = {6, 0};
static const char *ng15 = "\tmemory[7] = %b\n";
static int ng16[] = {7, 0};
static const char *ng17 = "C:/Users/Shiva Reddy/Desktop/FinalProject-Proj2/Control_Unit.v";
static const char *ng18 = "REGISTERS INITIALIZED";
static const char *ng19 = "READ ISTRUCTION SET TO 1";
static const char *ng20 = "test\\test.data";
static const char *ng21 = "test\\50001111_50001212.o";
static const char *ng22 = "Instruction READ : %b";
static const char *ng23 = "LOAD INSTRUCTION";
static const char *ng24 = "LOADED INSTRUCTION %b  :";
static const char *ng25 = "STORE INSTRUCTION";
static const char *ng26 = "STORE COMPLETE %b";
static const char *ng27 = "ADD INSTRUCTION";
static const char *ng28 = "ADD RESULT : %d";
static const char *ng29 = "SUB INSTRUCTION";
static const char *ng30 = "SUB RESULT : %d";
static const char *ng31 = "INVERT INSTRUCTION";
static const char *ng32 = "INVERT RESULT : %d";
static const char *ng33 = "<< INSTRUCTION";
static const char *ng34 = "<<  RESULT : %d";
static const char *ng35 = ">> INSTRUCTION";
static const char *ng36 = ">>  RESULT : %d";
static const char *ng37 = "AND INSTRUCTION";
static const char *ng38 = "&  RESULT : %d";
static int ng39[] = {8, 0};
static const char *ng40 = "OR INSTRUCTION";
static int ng41[] = {9, 0};
static const char *ng42 = "SET LESS THAN INSTRUCTION";
static int ng43[] = {10, 0};
static const char *ng44 = "BEZ INSTRUCTION";
static int ng45[] = {11, 0};
static const char *ng46 = "BNEZ INSTRUCTION";
static int ng47[] = {12, 0};
static const char *ng48 = "JUMP INSTRUCTION";

void Monitor_43_1(char *);
void Monitor_43_1(char *);


static void Monitor_43_1_Func(char *t0)
{
    char t4[16];
    char t9[8];
    char t20[8];
    char t31[8];
    char t42[8];
    char t53[8];
    char t64[8];
    char t75[8];
    char t86[8];
    char *t1;
    char *t2;
    char *t3;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;
    char *t19;
    char *t21;
    char *t22;
    char *t23;
    char *t24;
    char *t25;
    char *t26;
    char *t27;
    char *t28;
    char *t29;
    char *t30;
    char *t32;
    char *t33;
    char *t34;
    char *t35;
    char *t36;
    char *t37;
    char *t38;
    char *t39;
    char *t40;
    char *t41;
    char *t43;
    char *t44;
    char *t45;
    char *t46;
    char *t47;
    char *t48;
    char *t49;
    char *t50;
    char *t51;
    char *t52;
    char *t54;
    char *t55;
    char *t56;
    char *t57;
    char *t58;
    char *t59;
    char *t60;
    char *t61;
    char *t62;
    char *t63;
    char *t65;
    char *t66;
    char *t67;
    char *t68;
    char *t69;
    char *t70;
    char *t71;
    char *t72;
    char *t73;
    char *t74;
    char *t76;
    char *t77;
    char *t78;
    char *t79;
    char *t80;
    char *t81;
    char *t82;
    char *t83;
    char *t84;
    char *t85;
    char *t87;
    char *t88;
    char *t89;
    char *t90;
    char *t91;
    char *t92;
    char *t93;

LAB0:    t1 = (t0 + 3208);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = xsi_vlog_time(t4, 1000.0000000000000, 1000.0000000000000);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng0, 2, t0, (char)118, t4, 64);
    t6 = (t0 + 3048);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    t10 = (t0 + 3048);
    t11 = (t10 + 72U);
    t12 = *((char **)t11);
    t13 = (t0 + 3048);
    t14 = (t13 + 64U);
    t15 = *((char **)t14);
    t16 = ((char*)((ng2)));
    xsi_vlog_generic_get_array_select_value(t9, 16, t8, t12, t15, 2, 1, t16, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng1, 2, t0, (char)118, t9, 16);
    t17 = (t0 + 3048);
    t18 = (t17 + 56U);
    t19 = *((char **)t18);
    t21 = (t0 + 3048);
    t22 = (t21 + 72U);
    t23 = *((char **)t22);
    t24 = (t0 + 3048);
    t25 = (t24 + 64U);
    t26 = *((char **)t25);
    t27 = ((char*)((ng4)));
    xsi_vlog_generic_get_array_select_value(t20, 16, t19, t23, t26, 2, 1, t27, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng3, 2, t0, (char)118, t20, 16);
    t28 = (t0 + 3048);
    t29 = (t28 + 56U);
    t30 = *((char **)t29);
    t32 = (t0 + 3048);
    t33 = (t32 + 72U);
    t34 = *((char **)t33);
    t35 = (t0 + 3048);
    t36 = (t35 + 64U);
    t37 = *((char **)t36);
    t38 = ((char*)((ng6)));
    xsi_vlog_generic_get_array_select_value(t31, 16, t30, t34, t37, 2, 1, t38, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng5, 2, t0, (char)118, t31, 16);
    t39 = (t0 + 3048);
    t40 = (t39 + 56U);
    t41 = *((char **)t40);
    t43 = (t0 + 3048);
    t44 = (t43 + 72U);
    t45 = *((char **)t44);
    t46 = (t0 + 3048);
    t47 = (t46 + 64U);
    t48 = *((char **)t47);
    t49 = ((char*)((ng8)));
    xsi_vlog_generic_get_array_select_value(t42, 16, t41, t45, t48, 2, 1, t49, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng7, 2, t0, (char)118, t42, 16);
    t50 = (t0 + 3048);
    t51 = (t50 + 56U);
    t52 = *((char **)t51);
    t54 = (t0 + 3048);
    t55 = (t54 + 72U);
    t56 = *((char **)t55);
    t57 = (t0 + 3048);
    t58 = (t57 + 64U);
    t59 = *((char **)t58);
    t60 = ((char*)((ng10)));
    xsi_vlog_generic_get_array_select_value(t53, 16, t52, t56, t59, 2, 1, t60, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng9, 2, t0, (char)118, t53, 16);
    t61 = (t0 + 3048);
    t62 = (t61 + 56U);
    t63 = *((char **)t62);
    t65 = (t0 + 3048);
    t66 = (t65 + 72U);
    t67 = *((char **)t66);
    t68 = (t0 + 3048);
    t69 = (t68 + 64U);
    t70 = *((char **)t69);
    t71 = ((char*)((ng12)));
    xsi_vlog_generic_get_array_select_value(t64, 16, t63, t67, t70, 2, 1, t71, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng11, 2, t0, (char)118, t64, 16);
    t72 = (t0 + 3048);
    t73 = (t72 + 56U);
    t74 = *((char **)t73);
    t76 = (t0 + 3048);
    t77 = (t76 + 72U);
    t78 = *((char **)t77);
    t79 = (t0 + 3048);
    t80 = (t79 + 64U);
    t81 = *((char **)t80);
    t82 = ((char*)((ng14)));
    xsi_vlog_generic_get_array_select_value(t75, 16, t74, t78, t81, 2, 1, t82, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 0, 0, 3, ng13, 2, t0, (char)118, t75, 16);
    t83 = (t0 + 3048);
    t84 = (t83 + 56U);
    t85 = *((char **)t84);
    t87 = (t0 + 3048);
    t88 = (t87 + 72U);
    t89 = *((char **)t88);
    t90 = (t0 + 3048);
    t91 = (t90 + 64U);
    t92 = *((char **)t91);
    t93 = ((char*)((ng16)));
    xsi_vlog_generic_get_array_select_value(t86, 16, t85, t89, t92, 2, 1, t93, 32, 1);
    xsi_vlogfile_fwrite(*((unsigned int *)t3), 1, 0, 3, ng15, 2, t0, (char)118, t86, 16);

LAB1:    return;
}

static void Initial_27_0(char *t0)
{
    char t4[8];
    char t5[8];
    char *t1;
    char *t2;
    char *t3;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    unsigned int t14;
    int t15;
    char *t16;
    unsigned int t17;
    int t18;
    int t19;
    unsigned int t20;
    unsigned int t21;
    int t22;
    int t23;

LAB0:    t1 = (t0 + 4288U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(28, ng17);

LAB4:    xsi_set_current_line(29, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng2)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB5;

LAB6:    xsi_set_current_line(30, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng4)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB7;

LAB8:    xsi_set_current_line(31, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng6)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB9;

LAB10:    xsi_set_current_line(32, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng8)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB11;

LAB12:    xsi_set_current_line(33, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng10)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB13;

LAB14:    xsi_set_current_line(34, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng12)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB15;

LAB16:    xsi_set_current_line(35, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng14)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB17;

LAB18:    xsi_set_current_line(36, ng17);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 1928);
    t6 = (t0 + 1928);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 1928);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = ((char*)((ng16)));
    xsi_vlog_generic_convert_array_indices(t4, t5, t8, t11, 2, 1, t12, 32, 1);
    t13 = (t4 + 4);
    t14 = *((unsigned int *)t13);
    t15 = (!(t14));
    t16 = (t5 + 4);
    t17 = *((unsigned int *)t16);
    t18 = (!(t17));
    t19 = (t15 && t18);
    if (t19 == 1)
        goto LAB19;

LAB20:    xsi_set_current_line(37, ng17);
    xsi_vlogfile_write(1, 0, 0, ng18, 1, t0);
    xsi_set_current_line(38, ng17);
    t2 = ((char*)((ng4)));
    t3 = (t0 + 1768);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 4);
    xsi_set_current_line(39, ng17);
    xsi_vlogfile_write(1, 0, 0, ng19, 1, t0);
    xsi_set_current_line(41, ng17);
    t2 = (t0 + 3048);
    xsi_vlogfile_readmemb(ng20, 0, t2, 0, 0, 0, 0);
    xsi_set_current_line(42, ng17);
    *((int *)t4) = xsi_vlogfile_file_open_of_cname(ng21);
    t2 = (t4 + 4);
    *((int *)t2) = 0;
    t3 = (t0 + 3208);
    xsi_vlogvar_assign_value(t3, t4, 0, 0, 32);
    xsi_set_current_line(43, ng17);
    Monitor_43_1(t0);
    xsi_set_current_line(52, ng17);
    t2 = (t0 + 4096);
    xsi_process_wait(t2, 10000000LL);
    *((char **)t1) = &&LAB21;

LAB1:    return;
LAB5:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB6;

LAB7:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB8;

LAB9:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB10;

LAB11:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB12;

LAB13:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB14;

LAB15:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB16;

LAB17:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB18;

LAB19:    t20 = *((unsigned int *)t4);
    t21 = *((unsigned int *)t5);
    t22 = (t20 - t21);
    t23 = (t22 + 1);
    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t5), t23);
    goto LAB20;

LAB21:    xsi_set_current_line(53, ng17);
    t2 = (t0 + 3208);
    t3 = (t2 + 56U);
    t6 = *((char **)t3);
    xsi_vlogfile_fclose(*((unsigned int *)t6));
    goto LAB1;

}

static void Always_57_2(char *t0)
{
    char t6[8];
    char t37[8];
    char t39[8];
    char t65[8];
    char t66[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t7;
    char *t8;
    unsigned int t9;
    unsigned int t10;
    unsigned int t11;
    unsigned int t12;
    unsigned int t13;
    unsigned int t14;
    unsigned int t15;
    unsigned int t16;
    unsigned int t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;
    char *t21;
    char *t22;
    unsigned int t23;
    unsigned int t24;
    unsigned int t25;
    unsigned int t26;
    unsigned int t27;
    char *t28;
    char *t29;
    int t30;
    char *t31;
    char *t32;
    char *t33;
    char *t34;
    char *t35;
    char *t36;
    char *t38;
    char *t40;
    char *t41;
    char *t42;
    char *t43;
    char *t44;
    char *t45;
    char *t46;
    char *t47;
    int t48;
    int t49;
    int t50;
    int t51;
    char *t52;
    char *t53;
    char *t54;
    char *t55;
    char *t56;
    unsigned int t57;
    unsigned int t58;
    unsigned int t59;
    unsigned int t60;
    unsigned int t61;
    unsigned int t62;
    unsigned int t63;
    char *t64;
    char *t67;
    char *t68;
    char *t69;

LAB0:    t1 = (t0 + 4536U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(57, ng17);
    t2 = (t0 + 5104);
    *((int *)t2) = 1;
    t3 = (t0 + 4568);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(57, ng17);

LAB5:    xsi_set_current_line(58, ng17);
    t4 = (t0 + 1368U);
    t5 = *((char **)t4);
    t4 = ((char*)((ng2)));
    memset(t6, 0, 8);
    t7 = (t5 + 4);
    t8 = (t4 + 4);
    t9 = *((unsigned int *)t5);
    t10 = *((unsigned int *)t4);
    t11 = (t9 ^ t10);
    t12 = *((unsigned int *)t7);
    t13 = *((unsigned int *)t8);
    t14 = (t12 ^ t13);
    t15 = (t11 | t14);
    t16 = *((unsigned int *)t7);
    t17 = *((unsigned int *)t8);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB7;

LAB6:    if (t18 != 0)
        goto LAB8;

LAB9:    t22 = (t6 + 4);
    t23 = *((unsigned int *)t22);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 != 0);
    if (t27 > 0)
        goto LAB10;

LAB11:
LAB12:    goto LAB2;

LAB7:    *((unsigned int *)t6) = 1;
    goto LAB9;

LAB8:    t21 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t21) = 1;
    goto LAB9;

LAB10:    xsi_set_current_line(58, ng17);

LAB13:    xsi_set_current_line(60, ng17);
    t28 = (t0 + 1208U);
    t29 = *((char **)t28);
    xsi_vlogfile_write(1, 0, 0, ng22, 2, t0, (char)118, t29, 16);
    xsi_set_current_line(61, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t4 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 12);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t4);
    t12 = (t11 >> 12);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 15U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 15U);
    t5 = (t0 + 2248);
    xsi_vlogvar_assign_value(t5, t6, 0, 0, 4);
    xsi_set_current_line(63, ng17);
    t2 = (t0 + 2248);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);

LAB14:    t5 = ((char*)((ng2)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t5, 32);
    if (t30 == 1)
        goto LAB15;

LAB16:    t2 = ((char*)((ng4)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB17;

LAB18:    t2 = ((char*)((ng6)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB19;

LAB20:    t2 = ((char*)((ng8)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB21;

LAB22:    t2 = ((char*)((ng10)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB23;

LAB24:    t2 = ((char*)((ng12)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB25;

LAB26:    t2 = ((char*)((ng14)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB27;

LAB28:    t2 = ((char*)((ng16)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB29;

LAB30:    t2 = ((char*)((ng39)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB31;

LAB32:    t2 = ((char*)((ng41)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB33;

LAB34:    t2 = ((char*)((ng43)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB35;

LAB36:    t2 = ((char*)((ng45)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB37;

LAB38:    t2 = ((char*)((ng47)));
    t30 = xsi_vlog_unsigned_case_compare(t4, 4, t2, 32);
    if (t30 == 1)
        goto LAB39;

LAB40:
LAB41:    xsi_set_current_line(155, ng17);
    t2 = (t0 + 1768);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = ((char*)((ng4)));
    memset(t6, 0, 8);
    xsi_vlog_unsigned_add(t6, 32, t5, 4, t7, 32);
    t8 = (t0 + 1768);
    xsi_vlogvar_assign_value(t8, t6, 0, 0, 4);
    goto LAB12;

LAB15:    xsi_set_current_line(64, ng17);

LAB42:    xsi_set_current_line(65, ng17);
    xsi_vlogfile_write(1, 0, 0, ng23, 1, t0);
    xsi_set_current_line(66, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(67, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 0);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 0);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 63U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 63U);
    t7 = (t0 + 2888);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 7);
    xsi_set_current_line(68, ng17);
    t2 = (t0 + 3048);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 3048);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 3048);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    t34 = (t0 + 2888);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    memset(t37, 0, 8);
    xsi_vlog_unsigned_add(t37, 7, t33, 4, t36, 7);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t37, 7, 2);
    t38 = (t0 + 3368);
    xsi_vlogvar_assign_value(t38, t6, 0, 0, 16);
    xsi_set_current_line(69, ng17);
    t2 = (t0 + 3368);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng24, 2, t0, (char)118, t5, 16);
    goto LAB41;

LAB17:    xsi_set_current_line(71, ng17);

LAB43:    xsi_set_current_line(72, ng17);
    xsi_vlogfile_write(1, 0, 0, ng25, 1, t0);
    xsi_set_current_line(73, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(74, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 0);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 0);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 63U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 63U);
    t7 = (t0 + 2888);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 7);
    xsi_set_current_line(75, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(76, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2568);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 3048);
    t35 = (t0 + 3048);
    t36 = (t35 + 72U);
    t38 = *((char **)t36);
    t40 = (t0 + 3048);
    t41 = (t40 + 64U);
    t42 = *((char **)t41);
    t43 = (t0 + 3368);
    t44 = (t43 + 56U);
    t45 = *((char **)t44);
    xsi_vlog_generic_convert_array_indices(t37, t39, t38, t42, 2, 1, t45, 16, 2);
    t46 = (t37 + 4);
    t9 = *((unsigned int *)t46);
    t30 = (!(t9));
    t47 = (t39 + 4);
    t10 = *((unsigned int *)t47);
    t48 = (!(t10));
    t49 = (t30 && t48);
    if (t49 == 1)
        goto LAB44;

LAB45:    xsi_set_current_line(77, ng17);
    t2 = (t0 + 3048);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 3048);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 3048);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 3368);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 16, 2);
    xsi_vlogfile_write(1, 0, 0, ng26, 2, t0, (char)118, t6, 16);
    goto LAB41;

LAB19:    xsi_set_current_line(79, ng17);

LAB46:    xsi_set_current_line(80, ng17);
    xsi_vlogfile_write(1, 0, 0, ng27, 1, t0);
    xsi_set_current_line(81, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(82, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(83, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(84, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 1928);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    t38 = (t0 + 1928);
    t40 = (t38 + 72U);
    t41 = *((char **)t40);
    t42 = (t0 + 1928);
    t43 = (t42 + 64U);
    t44 = *((char **)t43);
    t45 = (t0 + 2568);
    t46 = (t45 + 56U);
    t47 = *((char **)t46);
    xsi_vlog_generic_get_array_select_value(t37, 16, t36, t41, t44, 2, 1, t47, 4, 2);
    memset(t39, 0, 8);
    xsi_vlog_unsigned_add(t39, 16, t6, 16, t37, 16);
    t52 = (t0 + 2728);
    xsi_vlogvar_assign_value(t52, t39, 0, 0, 4);
    xsi_set_current_line(85, ng17);
    t2 = (t0 + 2728);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng28, 2, t0, (char)118, t5, 4);
    goto LAB41;

LAB21:    xsi_set_current_line(87, ng17);

LAB47:    xsi_set_current_line(88, ng17);
    xsi_vlogfile_write(1, 0, 0, ng29, 1, t0);
    xsi_set_current_line(89, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(90, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(91, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(92, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 1928);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    t38 = (t0 + 1928);
    t40 = (t38 + 72U);
    t41 = *((char **)t40);
    t42 = (t0 + 1928);
    t43 = (t42 + 64U);
    t44 = *((char **)t43);
    t45 = (t0 + 2568);
    t46 = (t45 + 56U);
    t47 = *((char **)t46);
    xsi_vlog_generic_get_array_select_value(t37, 16, t36, t41, t44, 2, 1, t47, 4, 2);
    memset(t39, 0, 8);
    xsi_vlog_unsigned_minus(t39, 16, t6, 16, t37, 16);
    t52 = (t0 + 2728);
    xsi_vlogvar_assign_value(t52, t39, 0, 0, 4);
    xsi_set_current_line(93, ng17);
    t2 = (t0 + 2728);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng30, 2, t0, (char)118, t5, 4);
    goto LAB41;

LAB23:    xsi_set_current_line(95, ng17);

LAB48:    xsi_set_current_line(96, ng17);
    xsi_vlogfile_write(1, 0, 0, ng31, 1, t0);
    xsi_set_current_line(97, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(99, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(100, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t37, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    memset(t6, 0, 8);
    t34 = (t6 + 4);
    t35 = (t37 + 4);
    t9 = *((unsigned int *)t37);
    t10 = (~(t9));
    *((unsigned int *)t6) = t10;
    *((unsigned int *)t34) = 0;
    if (*((unsigned int *)t35) != 0)
        goto LAB50;

LAB49:    t15 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t15 & 65535U);
    t16 = *((unsigned int *)t34);
    *((unsigned int *)t34) = (t16 & 65535U);
    t36 = (t0 + 2728);
    xsi_vlogvar_assign_value(t36, t6, 0, 0, 4);
    xsi_set_current_line(101, ng17);
    t2 = (t0 + 2728);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng32, 2, t0, (char)118, t5, 4);
    goto LAB41;

LAB25:    xsi_set_current_line(103, ng17);

LAB51:    xsi_set_current_line(104, ng17);
    xsi_vlogfile_write(1, 0, 0, ng33, 1, t0);
    xsi_set_current_line(105, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(106, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(107, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(108, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 1928);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    t38 = (t0 + 1928);
    t40 = (t38 + 72U);
    t41 = *((char **)t40);
    t42 = (t0 + 1928);
    t43 = (t42 + 64U);
    t44 = *((char **)t43);
    t45 = (t0 + 2568);
    t46 = (t45 + 56U);
    t47 = *((char **)t46);
    xsi_vlog_generic_get_array_select_value(t37, 16, t36, t41, t44, 2, 1, t47, 4, 2);
    memset(t39, 0, 8);
    xsi_vlog_unsigned_lshift(t39, 16, t6, 16, t37, 16);
    t52 = (t0 + 2728);
    xsi_vlogvar_assign_value(t52, t39, 0, 0, 4);
    xsi_set_current_line(109, ng17);
    t2 = (t0 + 2728);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng34, 2, t0, (char)118, t5, 4);
    goto LAB41;

LAB27:    xsi_set_current_line(112, ng17);

LAB52:    xsi_set_current_line(113, ng17);
    xsi_vlogfile_write(1, 0, 0, ng35, 1, t0);
    xsi_set_current_line(114, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(115, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(116, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(117, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 1928);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    t38 = (t0 + 1928);
    t40 = (t38 + 72U);
    t41 = *((char **)t40);
    t42 = (t0 + 1928);
    t43 = (t42 + 64U);
    t44 = *((char **)t43);
    t45 = (t0 + 2568);
    t46 = (t45 + 56U);
    t47 = *((char **)t46);
    xsi_vlog_generic_get_array_select_value(t37, 16, t36, t41, t44, 2, 1, t47, 4, 2);
    memset(t39, 0, 8);
    xsi_vlog_unsigned_rshift(t39, 16, t6, 16, t37, 16);
    t52 = (t0 + 2728);
    xsi_vlogvar_assign_value(t52, t39, 0, 0, 4);
    xsi_set_current_line(118, ng17);
    t2 = (t0 + 2728);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng36, 2, t0, (char)118, t5, 4);
    goto LAB41;

LAB29:    xsi_set_current_line(120, ng17);

LAB53:    xsi_set_current_line(121, ng17);
    xsi_vlogfile_write(1, 0, 0, ng37, 1, t0);
    xsi_set_current_line(122, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(123, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(124, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(125, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 1928);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    t38 = (t0 + 1928);
    t40 = (t38 + 72U);
    t41 = *((char **)t40);
    t42 = (t0 + 1928);
    t43 = (t42 + 64U);
    t44 = *((char **)t43);
    t45 = (t0 + 2568);
    t46 = (t45 + 56U);
    t47 = *((char **)t46);
    xsi_vlog_generic_get_array_select_value(t37, 16, t36, t41, t44, 2, 1, t47, 4, 2);
    t9 = *((unsigned int *)t6);
    t10 = *((unsigned int *)t37);
    t11 = (t9 & t10);
    *((unsigned int *)t39) = t11;
    t52 = (t6 + 4);
    t53 = (t37 + 4);
    t54 = (t39 + 4);
    t12 = *((unsigned int *)t52);
    t13 = *((unsigned int *)t53);
    t14 = (t12 | t13);
    *((unsigned int *)t54) = t14;
    t15 = *((unsigned int *)t54);
    t16 = (t15 != 0);
    if (t16 == 1)
        goto LAB54;

LAB55:
LAB56:    t64 = (t0 + 2728);
    xsi_vlogvar_assign_value(t64, t39, 0, 0, 4);
    xsi_set_current_line(126, ng17);
    t2 = (t0 + 2728);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng38, 2, t0, (char)118, t5, 4);
    goto LAB41;

LAB31:    xsi_set_current_line(128, ng17);

LAB57:    xsi_set_current_line(129, ng17);
    xsi_vlogfile_write(1, 0, 0, ng40, 1, t0);
    xsi_set_current_line(130, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(131, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(132, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(133, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t6, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 1928);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    t38 = (t0 + 1928);
    t40 = (t38 + 72U);
    t41 = *((char **)t40);
    t42 = (t0 + 1928);
    t43 = (t42 + 64U);
    t44 = *((char **)t43);
    t45 = (t0 + 2568);
    t46 = (t45 + 56U);
    t47 = *((char **)t46);
    xsi_vlog_generic_get_array_select_value(t37, 16, t36, t41, t44, 2, 1, t47, 4, 2);
    t9 = *((unsigned int *)t6);
    t10 = *((unsigned int *)t37);
    t11 = (t9 | t10);
    *((unsigned int *)t39) = t11;
    t52 = (t6 + 4);
    t53 = (t37 + 4);
    t54 = (t39 + 4);
    t12 = *((unsigned int *)t52);
    t13 = *((unsigned int *)t53);
    t14 = (t12 | t13);
    *((unsigned int *)t54) = t14;
    t15 = *((unsigned int *)t54);
    t16 = (t15 != 0);
    if (t16 == 1)
        goto LAB58;

LAB59:
LAB60:    t64 = (t0 + 2728);
    xsi_vlogvar_assign_value(t64, t39, 0, 0, 4);
    xsi_set_current_line(134, ng17);
    t2 = (t0 + 2728);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    xsi_vlogfile_write(1, 0, 0, ng38, 2, t0, (char)118, t5, 4);
    goto LAB41;

LAB33:    xsi_set_current_line(136, ng17);

LAB61:    xsi_set_current_line(137, ng17);
    xsi_vlogfile_write(1, 0, 0, ng42, 1, t0);
    xsi_set_current_line(138, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 9);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 9);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2408);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(139, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 6);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 6);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2568);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(140, ng17);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t6 + 4);
    t5 = (t3 + 4);
    t9 = *((unsigned int *)t3);
    t10 = (t9 >> 3);
    *((unsigned int *)t6) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 3);
    *((unsigned int *)t2) = t12;
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 7U);
    t14 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t14 & 7U);
    t7 = (t0 + 2728);
    xsi_vlogvar_assign_value(t7, t6, 0, 0, 4);
    xsi_set_current_line(141, ng17);
    t2 = (t0 + 1928);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t7 = (t0 + 1928);
    t8 = (t7 + 72U);
    t21 = *((char **)t8);
    t22 = (t0 + 1928);
    t28 = (t22 + 64U);
    t29 = *((char **)t28);
    t31 = (t0 + 2408);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    xsi_vlog_generic_get_array_select_value(t39, 16, t5, t21, t29, 2, 1, t33, 4, 2);
    t34 = (t0 + 1928);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    t38 = (t0 + 1928);
    t40 = (t38 + 72U);
    t41 = *((char **)t40);
    t42 = (t0 + 1928);
    t43 = (t42 + 64U);
    t44 = *((char **)t43);
    t45 = (t0 + 2568);
    t46 = (t45 + 56U);
    t47 = *((char **)t46);
    xsi_vlog_generic_get_array_select_value(t65, 16, t36, t41, t44, 2, 1, t47, 4, 2);
    memset(t66, 0, 8);
    t52 = (t39 + 4);
    if (*((unsigned int *)t52) != 0)
        goto LAB63;

LAB62:    t53 = (t65 + 4);
    if (*((unsigned int *)t53) != 0)
        goto LAB63;

LAB66:    if (*((unsigned int *)t39) < *((unsigned int *)t65))
        goto LAB64;

LAB65:    memset(t37, 0, 8);
    t55 = (t66 + 4);
    t9 = *((unsigned int *)t55);
    t10 = (~(t9));
    t11 = *((unsigned int *)t66);
    t12 = (t11 & t10);
    t13 = (t12 & 1U);
    if (t13 != 0)
        goto LAB67;

LAB68:    if (*((unsigned int *)t55) != 0)
        goto LAB69;

LAB70:    t64 = (t37 + 4);
    t14 = *((unsigned int *)t37);
    t15 = *((unsigned int *)t64);
    t16 = (t14 || t15);
    if (t16 > 0)
        goto LAB71;

LAB72:    t17 = *((unsigned int *)t37);
    t18 = (~(t17));
    t19 = *((unsigned int *)t64);
    t20 = (t18 || t19);
    if (t20 > 0)
        goto LAB73;

LAB74:    if (*((unsigned int *)t64) > 0)
        goto LAB75;

LAB76:    if (*((unsigned int *)t37) > 0)
        goto LAB77;

LAB78:    memcpy(t6, t68, 8);

LAB79:    t69 = (t0 + 2728);
    xsi_vlogvar_assign_value(t69, t6, 0, 0, 4);
    goto LAB41;

LAB35:    xsi_set_current_line(143, ng17);

LAB80:    xsi_set_current_line(144, ng17);
    xsi_vlogfile_write(1, 0, 0, ng44, 1, t0);
    goto LAB41;

LAB37:    xsi_set_current_line(146, ng17);

LAB81:    xsi_set_current_line(147, ng17);
    xsi_vlogfile_write(1, 0, 0, ng46, 1, t0);
    goto LAB41;

LAB39:    xsi_set_current_line(149, ng17);

LAB82:    xsi_set_current_line(150, ng17);
    xsi_vlogfile_write(1, 0, 0, ng48, 1, t0);
    goto LAB41;

LAB44:    t11 = *((unsigned int *)t37);
    t12 = *((unsigned int *)t39);
    t50 = (t11 - t12);
    t51 = (t50 + 1);
    xsi_vlogvar_assign_value(t34, t6, 0, *((unsigned int *)t39), t51);
    goto LAB45;

LAB50:    t11 = *((unsigned int *)t6);
    t12 = *((unsigned int *)t35);
    *((unsigned int *)t6) = (t11 | t12);
    t13 = *((unsigned int *)t34);
    t14 = *((unsigned int *)t35);
    *((unsigned int *)t34) = (t13 | t14);
    goto LAB49;

LAB54:    t17 = *((unsigned int *)t39);
    t18 = *((unsigned int *)t54);
    *((unsigned int *)t39) = (t17 | t18);
    t55 = (t6 + 4);
    t56 = (t37 + 4);
    t19 = *((unsigned int *)t6);
    t20 = (~(t19));
    t23 = *((unsigned int *)t55);
    t24 = (~(t23));
    t25 = *((unsigned int *)t37);
    t26 = (~(t25));
    t27 = *((unsigned int *)t56);
    t57 = (~(t27));
    t30 = (t20 & t24);
    t48 = (t26 & t57);
    t58 = (~(t30));
    t59 = (~(t48));
    t60 = *((unsigned int *)t54);
    *((unsigned int *)t54) = (t60 & t58);
    t61 = *((unsigned int *)t54);
    *((unsigned int *)t54) = (t61 & t59);
    t62 = *((unsigned int *)t39);
    *((unsigned int *)t39) = (t62 & t58);
    t63 = *((unsigned int *)t39);
    *((unsigned int *)t39) = (t63 & t59);
    goto LAB56;

LAB58:    t17 = *((unsigned int *)t39);
    t18 = *((unsigned int *)t54);
    *((unsigned int *)t39) = (t17 | t18);
    t55 = (t6 + 4);
    t56 = (t37 + 4);
    t19 = *((unsigned int *)t55);
    t20 = (~(t19));
    t23 = *((unsigned int *)t6);
    t30 = (t23 & t20);
    t24 = *((unsigned int *)t56);
    t25 = (~(t24));
    t26 = *((unsigned int *)t37);
    t48 = (t26 & t25);
    t27 = (~(t30));
    t57 = (~(t48));
    t58 = *((unsigned int *)t54);
    *((unsigned int *)t54) = (t58 & t27);
    t59 = *((unsigned int *)t54);
    *((unsigned int *)t54) = (t59 & t57);
    goto LAB60;

LAB63:    t54 = (t66 + 4);
    *((unsigned int *)t66) = 1;
    *((unsigned int *)t54) = 1;
    goto LAB65;

LAB64:    *((unsigned int *)t66) = 1;
    goto LAB65;

LAB67:    *((unsigned int *)t37) = 1;
    goto LAB70;

LAB69:    t56 = (t37 + 4);
    *((unsigned int *)t37) = 1;
    *((unsigned int *)t56) = 1;
    goto LAB70;

LAB71:    t67 = ((char*)((ng4)));
    goto LAB72;

LAB73:    t68 = ((char*)((ng2)));
    goto LAB74;

LAB75:    xsi_vlog_unsigned_bit_combine(t6, 32, t67, 32, t68, 32);
    goto LAB79;

LAB77:    memcpy(t6, t67, 8);
    goto LAB79;

}

void Monitor_43_1(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;

LAB0:    t1 = (t0 + 3208);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t4 = (t0 + 4592);
    t5 = (t0 + 5120);
    xsi_vlog_fmonitor(*((unsigned int *)t3), (void *)Monitor_43_1_Func, t4, t5);

LAB1:    return;
}


extern void work_m_00000000003713243415_3901337038_init()
{
	static char *pe[] = {(void *)Initial_27_0,(void *)Always_57_2,(void *)Monitor_43_1};
	xsi_register_didat("work_m_00000000003713243415_3901337038", "isim/Risc_16_bit_isim_beh.exe.sim/work/m_00000000003713243415_3901337038.didat");
	xsi_register_executes(pe);
}
