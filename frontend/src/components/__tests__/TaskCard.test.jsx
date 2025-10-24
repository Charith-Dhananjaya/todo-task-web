import { render, fireEvent } from '@testing-library/react';
import TaskCard from '../TaskCard';

describe('TaskCard', () => {

  it('renders title/desc and calls onMarkDone with id', () => {
    
    const task = { id: 42, title: 'Demo', description: 'Desc', completed: false };
    const onMarkDone = vi.fn();

    const utils = render(<TaskCard task={task} onMarkDone={onMarkDone} />);

    expect(utils.getByTestId('task-title').textContent).toBe('Demo');
    expect(utils.getByTestId('task-desc').textContent).toBe('Desc');

    const btn = utils.getByTestId('done-btn-42');

    fireEvent.click(btn);

    expect(onMarkDone).toHaveBeenCalledWith(42);

  });
});
