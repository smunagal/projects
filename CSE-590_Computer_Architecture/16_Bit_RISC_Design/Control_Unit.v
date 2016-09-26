`timescale 1ns / 1ps
`include "Parameter.v"

module Control_Unit(
	input clk,
	output reg	[3:0] read_INSTRUCTION,
	input [15:0] INSTRUCTION,
	input [3:0] COUNTER
);
reg [15:0] R [7:0];
reg process_INSTRUCTION;
reg [3:0] opcode;
reg [3:0] R1;
reg [3:0] R2;
reg [3:0] RS;
reg [6:0] OFFSET;

reg [`col - 1:0] memory [`row_d - 1:0];
integer f;
reg [15:0] result;


// ALU CONTROLS

// your logic

initial
	begin	
		R[0]=0;
		R[1]=0;
		R[2]=0;
		R[3]=0;
		R[4]=0;
		R[5]=0;
		R[6]=0;
		R[7]=0;
		$display("REGISTERS INITIALIZED");
		read_INSTRUCTION=1;
		$display("READ ISTRUCTION SET TO 1");
		
		$readmemb("test\\test.data", memory);
		f = $fopen(`filename);
		$fmonitor(f, "time = %d\n", $time, 
		"\tmemory[0] = %b\n", memory[0],			
		"\tmemory[1] = %b\n", memory[1],
		"\tmemory[2] = %b\n", memory[2],
		"\tmemory[3] = %b\n", memory[3],
		"\tmemory[4] = %b\n", memory[4],
		"\tmemory[5] = %b\n", memory[5],
		"\tmemory[6] = %b\n", memory[6],
		"\tmemory[7] = %b\n", memory[7]);
		`simulation_time;
		$fclose(f);
		
	end
	
always @(COUNTER) begin
	if(COUNTER != 0) begin
	//$display("INSIDE CONTROL UNIT");
	$display("Instruction READ : %b",	INSTRUCTION );
	opcode = INSTRUCTION [15:12];
	// process Instruction 
	case(opcode)
		0: begin
				$display("LOAD INSTRUCTION");
				R1 = INSTRUCTION[11:9] ;
				OFFSET = INSTRUCTION[5:0];
				result =	memory[R1 + OFFSET];
				$display("LOADED INSTRUCTION %b  :" , result);
			end
		1: begin
				$display("STORE INSTRUCTION");
				R1 = INSTRUCTION[11:9] ;
				OFFSET = INSTRUCTION[5:0];
				R2 = INSTRUCTION[8:6];
				memory[result] = R[R2];
				$display("STORE COMPLETE %b", memory[result]);
			end
		2: begin
				$display("ADD INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = R[R1] + R[R2];
				$display("ADD RESULT : %d", RS);
			end
		3: begin
				$display("SUB INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = R[R1] - R[R2];
				$display("SUB RESULT : %d", RS);
			end
		4: begin
				$display("INVERT INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				//R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = ~(R[R1]);
				$display("INVERT RESULT : %d", RS);
			end
		5: begin
				$display("<< INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = R[R1]<< R[R2];
				$display("<<  RESULT : %d", RS);
				
			end
		6: begin
				$display(">> INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = R[R1]>> R[R2];
				$display(">>  RESULT : %d", RS);
			end
		7: begin
				$display("AND INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = R[R1] & R[R2];
				$display("&  RESULT : %d", RS);
			end
		8: begin
				$display("OR INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = R[R1] | R[R2];
				$display("&  RESULT : %d", RS);
			end
		9: begin
				$display("SET LESS THAN INSTRUCTION");
				R1 = INSTRUCTION[11:9];
				R2 = INSTRUCTION[8:6];
				RS = INSTRUCTION[5:3];
				RS = R[R1] < R[R2] ? 1 : 0;
			end
		10: begin
				$display("BEZ INSTRUCTION");
			end
		11: begin
				$display("BNEZ INSTRUCTION");
			end
		12: begin
				$display("JUMP INSTRUCTION");
			end
	endcase
	
	// End processing instruction
	read_INSTRUCTION = read_INSTRUCTION + 1;
	//$display("READ INSTRUCTION INCREMENTED : %d",	read_INSTRUCTION );
	
	end
end

endmodule







