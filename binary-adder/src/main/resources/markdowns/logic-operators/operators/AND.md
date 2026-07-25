# **AND Operator**

## 0️⃣1️⃣ 1. Truth Tables
The AND operator yields a high state ($9\text{V}$) **only** when both input signals are simultaneously high.

| Entry 1 (A) | Entry 2 (B) | Boolean Output | Voltage Output |
| :---: | :---: | :---: | :---: |
| Low | Low | **False** | $\approx 0\text{V}$ |
| High | Low | **False** | $\approx 0\text{V}$ |
| Low | High | **False** | $\approx 0\text{V}$ |
| High | High | **True** | **$9\text{V}$** |

---

## 🧠 2. Engineering Logic & The "0.7V Voltage Drop" Problem
A naive approach would implement the AND operator by placing two NPN transistors directly in series. 
> ![raw AND operator](../../../static/images/handbook/logic_circuits_diagrams/AND/AND_simple_assembly.png "raw AND operator")

However, physics introduces a critical flaw: **The Voltage Drop**.

* Every active silicon transistor reduces the voltage passing through its collector-emitter junction by approximately **$0.7\text{V}$**.
* Two transistors in series drop a $9\text{V}$ VCC source down to **$7.6\text{V}$** at the output.
* If this signal chains into another consecutive logic gate, the voltage drops further to **$6.2\text{V}$**.

Eventually, the signal degrades entirely, breaking the binary threshold and inducing systemic logical errors (bugs).

### 🛡️ The Solution: NAND + NOT Topology
To eliminate signal degradation, the AND gate is engineered by combining a **NAND block** with an inverting **NOT block**. In this architecture, the logic inputs do not carry the output load; instead, they merely route control current to the ground, maintaining a clean $9\text{V}$ or $0\text{V}$ rail-to-rail digital state.

> ![NAND + NOT form of AND operator](../../../static/images/handbook/logic_circuits_diagrams/AND/AND_assembled_as_NAND_NOt.png "NAND + NOT form of AND operator")

🔻 The downsides of this solution are basically more complexity, more components (cost💲) and the bigger difficult to assemble the circuit.

---

## 📊 Circuit State Analysis 

Here is how the current behaves dynamically across the four entry combinations:

### a) Both Entries are LOW
* **Simulator image:**
>![both entries on low](../../../static/images/handbook/logic_circuits_diagrams/AND/entry_cases/both_entries_low.png "both entries on low")

* **Status:** Output is **LOW** ($\approx 0.14\text{V}$).
* **Behavior:** The NAND transistors remain un-saturated (switched off). Current from the main source cannot bridge to the ground via the NAND path, forcing it into the base of the NOT transistor. This saturates the NOT transistor, cleanly shunting the final output straight to ground.

### b) Entry 1 is HIGH / Entry 2 is LOW
* **Simulator image:**
>![One entry is high](../../../static/images/handbook/logic_circuits_diagrams/AND/entry_cases/one_entry_high_1.png "One entry is high")

* **Status:** Output is **LOW**.
* **Behavior:** Even though the first transistor's base receives pressure, current cannot flow because the second transistor remains an open circuit. 
> ❓ **Why doesn't the first transistor saturate?** For a transistor to saturate, current must actively flow through its base to ground. If the path to $0\text{V}$ is blocked down the line, charge carrier movement stops completely, leaving the transistor inactive.

### c) Entry 1 is LOW / Entry 2 is HIGH
* **Simulator image:**
>![One entry is high](../../../static/images/handbook/logic_circuits_diagrams/AND/entry_cases/one_entry_high_2.png "One entry is high")

* **Status:** Output is **LOW**.
* **Behavior:** Mirroring the scenario above, the top transistor blocks the path completely, keeping the NAND block inert and the NOT block active.

### d) Both Entries are HIGH
* **Simulator image:**
> ![Both entries are high](../../../static/images/handbook/logic_circuits_diagrams/AND/entry_cases/both_entries_high.png "Both entries are high")

* **Status:** Output is **HIGH** ($9\text{V}$).
* **Behavior:** Both NAND transistors fully saturate, opening a path of least resistance directly to ground. This starvation cuts off the base current to the NOT transistor, turning it off. The main supply line is now forced entirely out to the **OUTPUT** terminal.

---

### 2.3. Bill of Materials (BOM) 🛒

Each individual AND operator module requires the following discrete components:

* **3x** $22\text{k }\Omega$ ($1/4\text{W}$) Carbon Film Resistors
* **2x** $330\text{ }\Omega$ ($1/4\text{W}$) Carbon Film Resistors
* **3x** BC-548 NPN Bipolar Junction Transistors (BJTs)
* **Power Requirements:** Dual $9\text{V}$ Input Rails + Dedicated $9\text{V}$ VCC Source

---

### 2.4. Hardware Gallery (Soldering & Assembly) 🛠️

> 💡 *Note: Transitioning from a temporary breadboard layout to a permanent soldered PCB ensures minimal contact resistance and long-term circuit stability.*

| Component Layout (Top View) | Solder Traces (Bottom View) |
| :---: | :---: |
| ![View 1](../../../static/images/handbook/assembled_logic_circuits/AND/view_1.jpg) | ![View 2](../../../static/images/handbook/assembled_logic_circuits/AND/view_2.jpg) |
