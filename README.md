# Cache Memory Simulator

This project implements a simple RAM and cache memory simulator in Java, with the goal of demonstrating, in a practical and educational way, how cache memory works internally in a computer system.

---

## Objectives

- Simulate the use of **RAM memory** and **Cache memory**.
- Demonstrate how cache speeds up access to main memory.
- Analyze performance metrics such as **hit rate** and **miss rate**.

---

## Concepts

**Cache memory** is a smaller and faster memory that temporarily stores frequently accessed data from RAM, reducing access time.

In this project, we implemented a simulation in Java using two arrays:

- `cache_endereco`: stores the RAM addresses currently loaded in the cache.
- `cache_conteudo`: stores the data associated with each address.

---

## How It Works

### Scenario

- RAM memory with **256 positions** (addresses 0–255).
- Cache memory with **8 positions**.

### Access Logic

For each access to a RAM address:

1. **Check whether the address is already in cache:**
   - **HIT:** Address found in cache; the data is read directly.
   - **MISS:** Address not in cache; data is fetched from RAM and stored in cache.
     - If there is space in cache, the address is stored in the next free slot.
     - If the cache is full, the oldest data is replaced (**FIFO policy**).

### At the end of execution

The program displays:

- Total number of accesses.
- Total number of **HITs**.
- Total number of **MISSES**.
- Cache hit rate percentage.
