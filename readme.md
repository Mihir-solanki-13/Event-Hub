# EventHub – Real-Time Event Streaming Platform

## Overview
EventHub is a simple real-time event streaming project built to understand how modern backend systems handle continuous data such as clicks, logs, or user actions.

The project uses a Kafka-compatible broker and a Scala-based producer to simulate real-world event generation.

This repository is built step by step, focusing on **clarity, fundamentals, and real-world relevance**.

---

## Why This Project
Most production systems today are **event-driven**, not batch-based.

Companies like Apple, Visa, Amazon, and Netflix rely on streaming systems to:
- process user actions
- track events in real time
- build analytics pipelines

This project was built to:
- understand how Kafka-style systems work internally
- learn producer–consumer concepts practically
- move beyond toy CRUD applications
- prepare for backend & system design interviews

---

## What Has Been Built So Far

### 1. Messaging Infrastructure
- A Kafka-compatible broker (Redpanda) is running locally using Docker.
- Kafka protocol is exposed on `localhost:9092`.

**Why:**  
This acts as the backbone where events are stored and delivered reliably.

---

### 2. Topic Creation
- A topic named `events` is created.

**Why:**  
Topics act as logical channels where related events are grouped.

---

### 3. Manual Testing (CLI)
- Events were produced and consumed using CLI tools.
- Verified:
  - messages are stored
  - offsets increase
  - messages can be read back

**Why:**  
Before writing application code, it’s important to validate the messaging system itself.

---

### 4. Scala Kafka Producer
- A small Scala application continuously sends JSON events to Kafka.
- Events simulate real-world data like user clicks.

Example event:
```json
{
  "eventType": "CLICK",
  "userId": "u1",
  "timestamp": "2025-01-01T12:00:00Z"
}
